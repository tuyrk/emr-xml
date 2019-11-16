package com.tyk.demo.entity.hospitalization;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/5/7 14:42 星期一
 * Description:
 */

public class Advice {
    private String id;
    private String director;/*科主任*/
    private String deputy;/*副主任医师*/
    private String attending;/*主治医师*/
    private String advanced;/*进修医师*/
    private String inpatient;/*住院医师*/
    private String nurse;/*责任护士*/
    private String records;/*病案质量*/
    private Date time;/*质控日期*/
    private String route;/*离院方式*/

    @Override
    public String toString() {
        return "Advice{" +
                "id='" + id + '\'' +
                ", director='" + director + '\'' +
                ", deputy='" + deputy + '\'' +
                ", attending='" + attending + '\'' +
                ", advanced='" + advanced + '\'' +
                ", inpatient='" + inpatient + '\'' +
                ", nurse='" + nurse + '\'' +
                ", records='" + records + '\'' +
                ", time=" + time +
                ", route='" + route + '\'' +
                '}';
    }

    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return sdf.format(time);
    }

    public void setTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            this.time = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDeputy() {
        return deputy;
    }

    public void setDeputy(String deputy) {
        this.deputy = deputy;
    }

    public String getAttending() {
        return attending;
    }

    public void setAttending(String attending) {
        this.attending = attending;
    }

    public String getAdvanced() {
        return advanced;
    }

    public void setAdvanced(String advanced) {
        this.advanced = advanced;
    }

    public String getInpatient() {
        return inpatient;
    }

    public void setInpatient(String inpatient) {
        this.inpatient = inpatient;
    }

    public String getNurse() {
        return nurse;
    }

    public void setNurse(String nurse) {
        this.nurse = nurse;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}