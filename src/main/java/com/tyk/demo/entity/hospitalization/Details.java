package com.tyk.demo.entity.hospitalization;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/5/7 14:24 星期一
 * Description:
 *
 */

public class Details {
    private String id;
    private String hospital;
    private String route;/*入院途径*/
    private String type;/*治疗类别*/
    private Date inTime;/*入院时间*/
    private Date outTime;/*出院时间*/
    private String inCategory;/*入院科别*/
    private String outCategory;/*出院科别*/
    private String transCategory;/*转院科别*/
    private Integer day;/*实际住院天数*/
    private String diagnosis;/*中医诊断*/
    private String fourDiagnosis;/*四诊信息*/
    private String syndromeType;/*证型*/
    private String treatment;/*治法*/
    private String diseaseBit;/*病位*/
    private String disease;/*病性*/
    private String chinese;/*中药处方*/
    private String western;/*西医诊断*/
    private String clinical;/*临床路径*/

    public String getInTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return sdf.format(inTime);
    }

    public void setInTime(String inTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            this.inTime = sdf.parse(inTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getOutTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return sdf.format(outTime);
    }

    public void setOutTime(String outTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            this.outTime = sdf.parse(outTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Details{" +
                "id='" + id + '\'' +
                ", hospital='" + hospital + '\'' +
                ", route='" + route + '\'' +
                ", type='" + type + '\'' +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                ", inCategory='" + inCategory + '\'' +
                ", outCategory='" + outCategory + '\'' +
                ", transCategory='" + transCategory + '\'' +
                ", day=" + day +
                ", diagnosis='" + diagnosis + '\'' +
                ", fourDiagnosis='" + fourDiagnosis + '\'' +
                ", syndromeType='" + syndromeType + '\'' +
                ", treatment='" + treatment + '\'' +
                ", diseaseBit='" + diseaseBit + '\'' +
                ", disease='" + disease + '\'' +
                ", chinese='" + chinese + '\'' +
                ", western='" + western + '\'' +
                ", clinical='" + clinical + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getInCategory() {
        return inCategory;
    }

    public void setInCategory(String inCategory) {
        this.inCategory = inCategory;
    }

    public String getOutCategory() {
        return outCategory;
    }

    public void setOutCategory(String outCategory) {
        this.outCategory = outCategory;
    }

    public String getTransCategory() {
        return transCategory;
    }

    public void setTransCategory(String transCategory) {
        this.transCategory = transCategory;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = Integer.valueOf(day);
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getFourDiagnosis() {
        return fourDiagnosis;
    }

    public void setFourDiagnosis(String fourDiagnosis) {
        this.fourDiagnosis = fourDiagnosis;
    }

    public String getSyndromeType() {
        return syndromeType;
    }

    public void setSyndromeType(String syndromeType) {
        this.syndromeType = syndromeType;
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

    public String getWestern() {
        return western;
    }

    public void setWestern(String western) {
        this.western = western;
    }

    public String getClinical() {
        return clinical;
    }

    public void setClinical(String clinical) {
        this.clinical = clinical;
    }
}