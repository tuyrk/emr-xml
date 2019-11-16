package com.tyk.demo.dao.outpatient;

import com.tyk.demo.XmlToDbApplicationTests;
import com.tyk.demo.entity.outpatient.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class OutpatientUserInfoDaoTest extends XmlToDbApplicationTests {

    @Autowired
    private OutpatientUserInfoDao outpatientUserInfoDao;

    @Test
    public void insertUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(String.valueOf(System.currentTimeMillis()));
        System.out.println(outpatientUserInfoDao.insertUserInfo(userInfo));
    }

    @Test
    public void selectUserInfo() {
        List<UserInfo> userInfoList = outpatientUserInfoDao.selectUserInfo(null);
        for (UserInfo userInfo : userInfoList) {
            System.out.println("userInfo = " + userInfo);
        }
    }
}