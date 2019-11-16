package com.tyk.demo.service;

import com.tyk.demo.entity.hospitalization.Hospitalization;
import com.tyk.demo.entity.outpatient.Outpatient;

import java.util.ArrayList;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/5/8 9:42 星期二
 * Description:
 */
public interface Dom4jParseXml {
    ArrayList<Hospitalization> getHospitalizationList(String filename);

    ArrayList<Outpatient> getOutpatientList(String filename);
}
