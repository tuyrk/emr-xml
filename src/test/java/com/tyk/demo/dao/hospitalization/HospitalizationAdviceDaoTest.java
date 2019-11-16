package com.tyk.demo.dao.hospitalization;

import com.tyk.demo.XmlToDbApplicationTests;
import com.tyk.demo.entity.hospitalization.Advice;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HospitalizationAdviceDaoTest extends XmlToDbApplicationTests {

    @Autowired
    private HospitalizationAdviceDao hospitalizationAdviceDao;

    @Test
    public void insertAdvice() {
        Advice advice = new Advice();
        advice.setId(String.valueOf(System.currentTimeMillis()));
        System.out.println(hospitalizationAdviceDao.insertAdvice(advice));
    }

    @Test
    public void selectAdvice() {
        List<Advice> adviceList = hospitalizationAdviceDao.selectAdvice(null);
        for (Advice advice : adviceList) {
            System.out.println("advice = " + advice);
        }
    }
}