package com.tyk.demo.entity.outpatient;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/5/7 15:23 星期一
 * Description:
 */

public class Details {
    private String id;
    private String department;/*科室*/
    private Date time;/*就诊时间*/
    private String fourDiagnosis;/*主诉*/
    private String diagnosis;/*临床诊断*/
    private String syndromeType;/*证型*/
    private String allergy;/*药物过敏史*/
    private String treatment;/*治法*/
    private String diseaseBit;/*病位*/
    private String disease;/*病性*/
    private String chinese;/*中药处方*/

    @Override
    public String toString() {
        return "Details{" +
                "id='" + id + '\'' +
                ", department='" + department + '\'' +
                ", time=" + time +
                ", fourDiagnosis='" + fourDiagnosis + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", syndromeType='" + syndromeType + '\'' +
                ", allergy='" + allergy + '\'' +
                ", treatment='" + treatment + '\'' +
                ", diseaseBit='" + diseaseBit + '\'' +
                ", disease='" + disease + '\'' +
                ", chinese='" + chinese + '\'' +
                '}';
    }

    public void setTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            this.time = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return sdf.format(time);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getFourDiagnosis() {
        return fourDiagnosis;
    }

    public void setFourDiagnosis(String fourDiagnosis) {
        this.fourDiagnosis = fourDiagnosis;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getSyndromeType() {
        return syndromeType;
    }

    public void setSyndromeType(String syndromeType) {
        this.syndromeType = syndromeType;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getDiseaseBit() {
        return diseaseBit;
    }

    public void setDiseaseBit(String diseaseBit) {
        this.diseaseBit = diseaseBit;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }
}