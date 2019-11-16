package com.tyk.demo.entity.hospitalization;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/5/7 23:30 星期一
 * Description:
 */

public class Hospitalization {
    private UserInfo userInfo;
    private Details details;
    private Advice advice;

    @Override
    public String toString() {
        return "Hospitalization{" +
                "userInfo=" + userInfo +
                ", details=" + details +
                ", advice=" + advice +
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

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}