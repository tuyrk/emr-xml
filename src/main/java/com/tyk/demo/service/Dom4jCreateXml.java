package com.tyk.demo.service;

import com.tyk.demo.entity.hospitalization.Hospitalization;
import com.tyk.demo.entity.outpatient.Outpatient;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/5/9 13:03 星期三
 * Description:
 */
public interface Dom4jCreateXml {
    boolean createHospitalization(List<Hospitalization> hospitalizationList, String filename);

    boolean createOutpatient(List<Outpatient> outpatientList, String filename);
}
