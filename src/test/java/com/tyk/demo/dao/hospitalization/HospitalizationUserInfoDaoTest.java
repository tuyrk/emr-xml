package com.tyk.demo.dao.hospitalization;

import com.tyk.demo.XmlToDbApplicationTests;
import com.tyk.demo.entity.hospitalization.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HospitalizationUserInfoDaoTest extends XmlToDbApplicationTests {

    @Autowired
    private HospitalizationUserInfoDao hospitalizationUserInfoDao;

    @Test
    public void insertUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(String.valueOf(System.currentTimeMillis()));
        System.out.println(hospitalizationUserInfoDao.insertUserInfo(userInfo));
    }

    @Test
    public void selectUserInfo() {
        List<UserInfo> userInfoList = hospitalizationUserInfoDao.selectUserInfo(null);
        for (UserInfo userInfo : userInfoList) {
            System.out.println("userInfo = " + userInfo);
        }
    }
}