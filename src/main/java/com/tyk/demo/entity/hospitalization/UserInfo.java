package com.tyk.demo.entity.hospitalization;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/5/7 14:09 星期一
 * Description:
 */
public class UserInfo {
    private String id;
    private String name;/*姓名*/
    private String sex;/*性别*/
    private Date birth;/*出生日期*/
    private Integer age;/*年龄*/
    private String position;/*职业*/
    private String phone;/*电话*/
    private Integer count;/*住院次数*/
    private String marriage;/*婚姻*/
    private String nation;/*民族*/
    private String idNumber;/*身份证号*/
    private String domicile;/*现居住地*/
    private String homeplace;/*出生地*/

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birth=" + birth +
                ", age=" + age +
                ", position='" + position + '\'' +
                ", phone='" + phone + '\'' +
                ", count=" + count +
                ", marriage='" + marriage + '\'' +
                ", nation='" + nation + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", domicile='" + domicile + '\'' +
                ", homeplace='" + homeplace + '\'' +
                '}';
    }

    public void setBirth(String birth) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.birth = sdf.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getBirth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(birth);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = Integer.valueOf(age);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = Integer.valueOf(count);
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getHomeplace() {
        return homeplace;
    }

    public void setHomeplace(String homeplace) {
        this.homeplace = homeplace;
    }
}