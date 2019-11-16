package com.tyk.demo.entity.outpatient;

import lombok.Data;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/5/7 23:37 星期一
 * Description:
 */

public class Outpatient {
    private UserInfo userInfo;
    private Details details;

    @Override
    public String toString() {
        return "Outpatient{" +
                "userInfo=" + userInfo +
                ", details=" + details +
                '}';
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
}