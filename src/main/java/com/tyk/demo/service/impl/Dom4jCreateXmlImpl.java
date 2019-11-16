package com.tyk.demo.service.impl;

import com.tyk.demo.entity.hospitalization.*;
import com.tyk.demo.entity.outpatient.Outpatient;
import com.tyk.demo.service.Dom4jCreateXml;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/5/9 13:05 星期三
 * Description:
 */
@Service
public class Dom4jCreateXmlImpl implements Dom4jCreateXml {
    @Override
    public boolean createHospitalization(List<Hospitalization> hospitalizationList, String filename) {
        try {
            File out = new File(filename);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileOutputStream(out), format);
            writer.write(createHospitalizationDocument(hospitalizationList));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean createOutpatient(List<Outpatient> outpatientList, String filename) {
        try {
            File out = new File(filename);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileOutputStream(out), format);
            writer.write(createOutpatientDocument(outpatientList));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Document createHospitalizationDocument(List<Hospitalization> hospitalizationList) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement(Hospitalization.class.getSimpleName() + 's');
        String className = Hospitalization.class.getName();
        root.addAttribute("namespace", className.substring(0, className.lastIndexOf('.')));
        UserInfo userInfo;
        Details details;
        Hospital hospital;
        for (Hospitalization hospitalization : hospitalizationList) {
            Element hospitalizationElement = root.addElement(Hospitalization.class.getSimpleName());
            userInfo = hospitalization.getUserInfo();
            details = hospitalization.getDetails();
            Advice advice = hospitalization.getAdvice();
            hospital = new Hospital(details.getId(), details.getHospital());
            hospitalizationElement.add(getDocument(hospital));
            hospitalizationElement.add(getDocument(userInfo));
            hospitalizationElement.add(getDocument(details));
            hospitalizationElement.add(getDocument(advice));
        }
        return document;
    }

    private Document createOutpatientDocument(List<Outpatient> outpatientList) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement(Outpatient.class.getSimpleName() + 's');
        String className = Outpatient.class.getName();
        root.addAttribute("namespace", className.substring(0, className.lastIndexOf('.')));
        com.tyk.demo.entity.outpatient.UserInfo userInfo;
        com.tyk.demo.entity.outpatient.Details details;
        for (Outpatient outpatient : outpatientList) {
            Element outpatientElement = root.addElement(Outpatient.class.getSimpleName());
            userInfo = outpatient.getUserInfo();
            details = outpatient.getDetails();
            outpatientElement.addAttribute("id", userInfo.getId());
            outpatientElement.add(getDocument(userInfo));
            outpatientElement.add(getDocument(details));
        }
        return document;
    }

    private Element getDocument(Object obj) {
        Element root = DocumentHelper.createElement(obj.getClass().getSimpleName());
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                String name = field.getName();
                if (name.equals("id") && (!obj.getClass().getName().equals(Hospital.class.getName()))) {
                    continue;
                }
                if (!name.equals("serialVersionUID")) {
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    Method getM = obj.getClass().getMethod("get" + name);
                    Element node = root.addElement(name);
                    node.setText(String.valueOf(getM.invoke(obj)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return root;
    }
}
