package com.tyk.demo.service.impl;

import com.tyk.demo.entity.hospitalization.*;
import com.tyk.demo.entity.outpatient.Outpatient;
import com.tyk.demo.service.Dom4jParseXml;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/5/7 23:18 星期一
 * Description:
 */
@Service
public class Dom4jParseXmlImpl implements Dom4jParseXml {

        public ArrayList<Hospitalization> getHospitalizationList(String filename) {
            ArrayList<Hospitalization> hospitalizationArrayList = new ArrayList<>();

            // Dom4j解析xml
            // 创建的对象reader
            SAXReader reader = new SAXReader();

            try {
                FileInputStream fileInputStream = new FileInputStream(filename);
                // 通过reader对象的read方法加载xml文件，获取document对象
                Document document = reader.read(fileInputStream);
                // 通过document对象获取根节点
                Element hospitalizationsElement = document.getRootElement();
                // 通过element对象的elementIterator方法获取迭代器
                Iterator it = hospitalizationsElement.elementIterator();
                // 全局变量记录第几条数据
                // 遍历迭代器，获取根节点中的每一条数据
                Hospitalization hospitalization;
                while (it.hasNext()) {
                    hospitalization = new Hospitalization();
                    Element hospitalizationElement = (Element) it.next();
//                Hospital hospital = getHospitalizationHospital(hospitalizationElement.element("Hospital"));
//                hospitalization.setUserInfo(getHospitalizationUserInfo(hospitalizationElement.element("UserInfo"), hospital));
//                hospitalization.setDetails(getHospitalizationDetails(hospitalizationElement.element("Details"), hospital));
//                hospitalization.setAdvice(getHospitalizationAdvice(hospitalizationElement.element("Advice"), hospital));

                    Hospital hospital = (Hospital) getObject(hospitalizationElement, new Hospital());
                    UserInfo userInfo = (UserInfo) getObject(hospitalizationElement, new UserInfo());
                    Details details = (Details) getObject(hospitalizationElement, new Details());
                    Advice advice = (Advice) getObject(hospitalizationElement, new Advice());
                    userInfo.setId(hospital.getId());
                    details.setId(hospital.getId());
                    details.setHospital(hospital.getHospital());
                    advice.setId(hospital.getId());
                    hospitalization.setUserInfo(userInfo);
                    hospitalization.setDetails(details);
                    hospitalization.setAdvice(advice);
                    hospitalizationArrayList.add(hospitalization);
                }
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
            return hospitalizationArrayList;
        }

        public ArrayList<Outpatient> getOutpatientList(String filename) {
            ArrayList<Outpatient> outpatientArrayList = new ArrayList<>();

            // Dom4j解析xml
            // 创建的对象reader
            SAXReader reader = new SAXReader();

            try {
                FileInputStream fileInputStream = new FileInputStream(filename);
                // 通过reader对象的read方法加载xml文件，获取document对象
                Document document = reader.read(fileInputStream);
                // 通过document对象获取根节点
                Element outpatientsElement = document.getRootElement();
                // 通过element对象的elementIterator方法获取迭代器
                Iterator it = outpatientsElement.elementIterator();
                // 全局变量记录第几条数据
                // 遍历迭代器，获取根节点中的每一条数据
                while (it.hasNext()) {
                    Outpatient outpatient = new Outpatient();
                    Element outpatientElement = (Element) it.next();
                    String id = outpatientElement.attribute("id").getStringValue();
//                outpatient.setUserInfo(getOutpatientUserInfo(outpatientElement.element("UserInfo"), id));
//                outpatient.setDetails(getOutpatientDetails(outpatientElement.element("Details"), id));
                    com.tyk.demo.entity.outpatient.UserInfo userInfo = (com.tyk.demo.entity.outpatient.UserInfo) getObject(outpatientElement, new com.tyk.demo.entity.outpatient.UserInfo());
                    com.tyk.demo.entity.outpatient.Details details = (com.tyk.demo.entity.outpatient.Details) getObject(outpatientElement, new com.tyk.demo.entity.outpatient.Details());
                    userInfo.setId(id);
                    details.setId(id);
                    outpatient.setUserInfo(userInfo);
                    outpatient.setDetails(details);
                    outpatientArrayList.add(outpatient);
                }
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
            return outpatientArrayList;
        }

        private Object getObject(Element ObjectElement, Object obj) {
            Element objElement = ObjectElement.element(obj.getClass().getSimpleName());
            String name = "";
            try {
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field field : fields) {
                    name = field.getName();
                    if (name.equals("id") && (!obj.getClass().equals(Hospital.class))) {
                        continue;
                    }
                    if (!name.equals("serialVersionUID")) {
                        name = name.substring(0, 1).toUpperCase() + name.substring(1);
                        Method setM = obj.getClass().getMethod("set" + name, String.class);
                        setM.invoke(obj, objElement.element(name).getStringValue());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return obj;
        }

    @Deprecated
    private com.tyk.demo.entity.hospitalization.Hospital getHospitalizationHospital(Element hospitalElement) {
        Hospital hospital = new Hospital();
        Iterator itt = hospitalElement.elementIterator();
        while (itt.hasNext()) {
            Element hospitalChild = (Element) itt.next();
            switch (hospitalChild.getName()) {
                case "Id":
                    hospital.setId(hospitalChild.getStringValue());
                    break;
                case "Hospital":
                    hospital.setHospital(hospitalChild.getStringValue());
                    break;
            }
        }
        return hospital;
    }

    @Deprecated
    private com.tyk.demo.entity.hospitalization.UserInfo getHospitalizationUserInfo(Element userInfoElement, Hospital hospital) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(hospital.getId());
        Iterator itt = userInfoElement.elementIterator();

        while (itt.hasNext()) {
            Element userInfoChild = (Element) itt.next();
            switch (userInfoChild.getName()) {
                case "Name":
                    userInfo.setName(userInfoChild.getStringValue());
                    break;
                case "Sex":
                    userInfo.setSex(userInfoChild.getStringValue());
                    break;
                case "Birth":
                    userInfo.setBirth(userInfoChild.getStringValue());
                    break;
                case "Age":
                    userInfo.setAge(userInfoChild.getStringValue());
                    break;
                case "Position":
                    userInfo.setPosition(userInfoChild.getStringValue());
                    break;
                case "Phone":
                    userInfo.setPhone(userInfoChild.getStringValue());
                    break;
                case "Count":
                    userInfo.setCount(userInfoChild.getStringValue());
                    break;
                case "Marriage":
                    userInfo.setMarriage(userInfoChild.getStringValue());
                    break;
                case "Nation":
                    userInfo.setNation(userInfoChild.getStringValue());
                    break;
                case "IdNumber":
                    userInfo.setIdNumber(userInfoChild.getStringValue());
                    break;
                case "Domicile":
                    userInfo.setDomicile(userInfoChild.getStringValue());
                    break;
                case "Homeplace":
                    userInfo.setHomeplace(userInfoChild.getStringValue());
                    break;
            }
        }
        return userInfo;
    }

    @Deprecated
    private com.tyk.demo.entity.hospitalization.Details getHospitalizationDetails(Element detailsElement, Hospital hospital) {
        Details details = new Details();
        details.setId(hospital.getId());
        details.setHospital(hospital.getHospital());
        Iterator itt = detailsElement.elementIterator();
        while (itt.hasNext()) {
            Element detailsChild = (Element) itt.next();
            switch (detailsChild.getName()) {
                case "Route":
                    details.setRoute(detailsChild.getStringValue());
                    break;
                case "Type":
                    details.setType(detailsChild.getStringValue());
                    break;
                case "InTime":
                    details.setInTime(detailsChild.getStringValue());
                    break;
                case "OutTime":
                    details.setOutTime(detailsChild.getStringValue());
                    break;
                case "InCategory":
                    details.setInCategory(detailsChild.getStringValue());
                    break;
                case "OutCategory":
                    details.setOutCategory(detailsChild.getStringValue());
                    break;
                case "TransCategory":
                    details.setTransCategory(detailsChild.getStringValue());
                    break;
                case "Day":
                    details.setDay(detailsChild.getStringValue());
                    break;
                case "Diagnosis":
                    details.setDiagnosis(detailsChild.getStringValue());
                    break;
                case "FourDiagnosis":
                    details.setFourDiagnosis(detailsChild.getStringValue());
                    break;
                case "SyndromeType":
                    details.setSyndromeType(detailsChild.getStringValue());
                    break;
                case "Treatment":
                    details.setTreatment(detailsChild.getStringValue());
                    break;
                case "DiseaseBit":
                    details.setDiseaseBit(detailsChild.getStringValue());
                    break;
                case "Disease":
                    details.setDisease(detailsChild.getStringValue());
                    break;
                case "Chinese":
                    details.setChinese(detailsChild.getStringValue());
                    break;
                case "Western":
                    details.setWestern(detailsChild.getStringValue());
                    break;
                case "Clinical":
                    details.setClinical(detailsChild.getStringValue());
                    break;
            }
        }
        return details;
    }

    @Deprecated
    private com.tyk.demo.entity.hospitalization.Advice getHospitalizationAdvice(Element adviceElement, Hospital hospital) {
        Advice advice = new Advice();
        advice.setId(hospital.getId());
        Iterator itt = adviceElement.elementIterator();
        while (itt.hasNext()) {
            Element adviceChild = (Element) itt.next();
            switch (adviceChild.getName()) {
                case "Director":
                    advice.setDirector(adviceChild.getStringValue());
                    break;
                case "Deputy":
                    advice.setDeputy(adviceChild.getStringValue());
                    break;
                case "Attending":
                    advice.setAttending(adviceChild.getStringValue());
                    break;
                case "Advanced":
                    advice.setAdvanced(adviceChild.getStringValue());
                    break;
                case "Inpatient":
                    advice.setInpatient(adviceChild.getStringValue());
                    break;
                case "Nurse":
                    advice.setNurse(adviceChild.getStringValue());
                    break;
                case "Records":
                    advice.setRecords(adviceChild.getStringValue());
                    break;
                case "Time":
                    advice.setTime(adviceChild.getStringValue());
                    break;
                case "Route":
                    advice.setRoute(adviceChild.getStringValue());
                    break;
            }
        }
        return advice;
    }

    @Deprecated
    private com.tyk.demo.entity.outpatient.UserInfo getOutpatientUserInfo(Element userInfoElement, String id) {
        com.tyk.demo.entity.outpatient.UserInfo userInfo = new com.tyk.demo.entity.outpatient.UserInfo();
        userInfo.setId(id);
        Iterator itt = userInfoElement.elementIterator();
        while (itt.hasNext()) {
            Element userInfoChild = (Element) itt.next();
            switch (userInfoChild.getName()) {
                case "Name":
                    userInfo.setName(userInfoChild.getStringValue());
                    break;
                case "Sex":
                    userInfo.setSex(userInfoChild.getStringValue());
                    break;
                case "Age":
                    userInfo.setAge(userInfoChild.getStringValue());
                    break;
                case "Phone":
                    userInfo.setPhone(userInfoChild.getStringValue());
                    break;
                case "IdNumber":
                    userInfo.setIdNumber(userInfoChild.getStringValue());
                    break;
                case "Address":
                    userInfo.setAddress(userInfoChild.getStringValue());
                    break;
            }
        }
        return userInfo;
    }

    @Deprecated
    private com.tyk.demo.entity.outpatient.Details getOutpatientDetails(Element detailsElement, String id) {
        com.tyk.demo.entity.outpatient.Details details = new com.tyk.demo.entity.outpatient.Details();
        details.setId(id);
        Iterator itt = detailsElement.elementIterator();
        while (itt.hasNext()) {
            Element detailsChild = (Element) itt.next();
            switch (detailsChild.getName()) {
                case "Department":
                    details.setDepartment(detailsChild.getStringValue());
                    break;
                case "Time":
                    details.setTime(detailsChild.getStringValue());
                    break;
                case "FourDiagnosis":
                    details.setFourDiagnosis(detailsChild.getStringValue());
                    break;
                case "Diagnosis":
                    details.setDiagnosis(detailsChild.getStringValue());
                    break;
                case "SyndromeType":
                    details.setSyndromeType(detailsChild.getStringValue());
                    break;
                case "Allergy":
                    details.setAllergy(detailsChild.getStringValue());
                    break;
                case "Treatment":
                    details.setTreatment(detailsChild.getStringValue());
                    break;
                case "DiseaseBit":
                    details.setDiseaseBit(detailsChild.getStringValue());
                    break;
                case "Disease":
                    details.setDisease(detailsChild.getStringValue());
                    break;
                case "Chinese":
                    details.setChinese(detailsChild.getStringValue());
                    break;
            }
        }
        return details;
    }
}