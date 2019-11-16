package com.tyk.demo.entity.outpatient;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/5/7 15:19 星期一
 * Description:
 */

public class UserInfo {
    private String id;
    private String name;/*姓名*/
    private String sex;/*性别*/
    private Integer age;/*年龄*/

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    private String phone;/*联系电话*/
    private String idNumber;/*身份证*/
    private String address;/*单位或家庭住址*/

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

    public Integer getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = Integer.valueOf(age);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}