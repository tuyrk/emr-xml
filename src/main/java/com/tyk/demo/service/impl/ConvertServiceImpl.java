package com.tyk.demo.service.impl;

import com.tyk.demo.dao.hospitalization.HospitalizationAdviceDao;
import com.tyk.demo.dao.hospitalization.HospitalizationDetailsDao;
import com.tyk.demo.dao.hospitalization.HospitalizationUserInfoDao;
import com.tyk.demo.dao.outpatient.OutpatientDetailsDao;
import com.tyk.demo.dao.outpatient.OutpatientUserInfoDao;
import com.tyk.demo.entity.hospitalization.Advice;
import com.tyk.demo.entity.hospitalization.Details;
import com.tyk.demo.entity.hospitalization.Hospitalization;
import com.tyk.demo.entity.hospitalization.UserInfo;
import com.tyk.demo.entity.outpatient.Outpatient;
import com.tyk.demo.service.ConvertService;
import com.tyk.demo.service.Dom4jCreateXml;
import com.tyk.demo.service.Dom4jParseXml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/5/8 22:19 星期二
 * Description:
 */
@Slf4j
@Service
public class ConvertServiceImpl implements ConvertService {

    @Autowired
    private Dom4jParseXml dom4jParseXml;
    @Autowired
    private Dom4jCreateXml dom4jCreateXml;
    @Autowired
    private HospitalizationUserInfoDao hospitalizationUserInfoDao;
    @Autowired
    private HospitalizationDetailsDao hospitalizationDetailsDao;
    @Autowired
    private HospitalizationAdviceDao hospitalizationAdviceDao;

    @Autowired
    private OutpatientUserInfoDao outpatientUserInfoDao;
    @Autowired
    private OutpatientDetailsDao outpatientDetailsDao;

    @Override
    @Transactional
    public boolean xml2db(String filename) {
        try {
            if (filename.contains("hospitalization")) {
                List<Hospitalization> hospitalizationList = dom4jParseXml.getHospitalizationList(filename);
                for (Hospitalization hospitalization : hospitalizationList) {
                    if (hospitalizationUserInfoDao.selectUserInfo(hospitalization.getUserInfo().getId()).size() == 0) {
                        log.info("【XML to DB】-hospitalization.xml->" + hospitalization);
                        hospitalizationUserInfoDao.insertUserInfo(hospitalization.getUserInfo());
                        hospitalizationDetailsDao.insertDetails(hospitalization.getDetails());
                        hospitalizationAdviceDao.insertAdvice(hospitalization.getAdvice());
                    } else {
                        log.info("【XML to DB】-" + hospitalization + "已经存在,进行更新");
                        hospitalizationUserInfoDao.updateUserInfo(hospitalization.getUserInfo());
                        hospitalizationDetailsDao.updateDetails(hospitalization.getDetails());
                        hospitalizationAdviceDao.updateAdvice(hospitalization.getAdvice());
                    }
                }
            } else if (filename.contains("outpatient")) {
                List<Outpatient> outpatientList = dom4jParseXml.getOutpatientList(filename);
                for (Outpatient outpatient : outpatientList) {
                    if (outpatientUserInfoDao.selectUserInfo(outpatient.getUserInfo().getId()).size() == 0) {
                        log.info("【XML to DB】-outpatient.xml->" + outpatient);
                        outpatientUserInfoDao.insertUserInfo(outpatient.getUserInfo());
                        outpatientDetailsDao.insertDetails(outpatient.getDetails());
                    } else {
                        log.info("【XML to DB】-" + outpatient + "已经存在,进行更新");
                        outpatientUserInfoDao.updateUserInfo(outpatient.getUserInfo());
                        outpatientDetailsDao.updateDetails(outpatient.getDetails());
                    }

                }
            } else {
                log.error("请标准命名xml文件{ \"hospitalization.xml\", \"outpatient\" }");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean db2xml(String filename) {
        try {
            if (filename.contains("hospitalization")) {
                List<UserInfo> userInfoList = hospitalizationUserInfoDao.selectUserInfo(null);
                List<Details> detailsList = hospitalizationDetailsDao.selectDetails(null);
                List<Advice> adviceList = hospitalizationAdviceDao.selectAdvice(null);
                int n = userInfoList.size();
                List<Hospitalization> hospitalizationList = new ArrayList<>();
                Hospitalization hospitalization;
                for (int i = 0; i < n; i++) {
                    hospitalization = new Hospitalization();
                    hospitalization.setUserInfo(userInfoList.get(i));
                    hospitalization.setDetails(detailsList.get(i));
                    hospitalization.setAdvice(adviceList.get(i));
                    hospitalizationList.add(hospitalization);
                    log.info("【DB to XML】-hospitalization.xml->" + hospitalization);
                }
                return dom4jCreateXml.createHospitalization(hospitalizationList, filename);
            } else if (filename.contains("outpatient")) {
                List<com.tyk.demo.entity.outpatient.UserInfo> userInfoList = outpatientUserInfoDao.selectUserInfo(null);
                List<com.tyk.demo.entity.outpatient.Details> detailsList = outpatientDetailsDao.selectDetails(null);
                int n = userInfoList.size();
                List<Outpatient> outpatientList = new ArrayList<>();
                Outpatient outpatient;
                for (int i = 0; i < n; i++) {
                    outpatient = new Outpatient();
                    outpatient.setUserInfo(userInfoList.get(i));
                    outpatient.setDetails(detailsList.get(i));
                    outpatientList.add(outpatient);
                    log.info("【DB to XML】-outpatient.xml->" + outpatient);
                }
                return dom4jCreateXml.createOutpatient(outpatientList, filename);
            } else {
                log.error("请标准命名xml文件{ \"hospitalization.xml\", \"outpatient\" }");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
