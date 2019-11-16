package com.tyk.demo.dao.hospitalization;

import com.tyk.demo.XmlToDbApplicationTests;
import com.tyk.demo.entity.hospitalization.Details;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HospitalizationDetailsDaoTest extends XmlToDbApplicationTests {

    @Autowired
    private HospitalizationDetailsDao hospitalizationDetailsDao;

    @Test
    public void insertDetails() {
        Details details = new Details();
        details.setId(String.valueOf(System.currentTimeMillis()));
        System.out.println(hospitalizationDetailsDao.insertDetails(details));
    }

    @Test
    public void selectDetails() {
        List<Details> detailsList = hospitalizationDetailsDao.selectDetails(null);
        for (Details details : detailsList) {
            System.out.println("details = " + details);
        }
    }
}