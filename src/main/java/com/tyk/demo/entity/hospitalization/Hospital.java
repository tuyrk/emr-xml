package com.tyk.demo.entity.hospitalization;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/5/8 14:09 星期二
 * Description:
 */

public class Hospital {
    private String id;
    private String hospital;

    @Override
    public String toString() {
        return "Hospital{" +
                "id='" + id + '\'' +
                ", hospital='" + hospital + '\'' +
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

    public Hospital() {
    }

    public Hospital(String id, String hospital) {
        this.id = id;
        this.hospital = hospital;
    }
}