package com.tyk.demo.dao.outpatient;

import com.tyk.demo.XmlToDbApplicationTests;
import com.tyk.demo.entity.outpatient.Details;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class OutpatientDetailsDaoTest extends XmlToDbApplicationTests {

    @Autowired
    private OutpatientDetailsDao outpatientDetailsDao;

    @Test
    public void insertDetails() {
        Details details = new Details();
        details.setId(String.valueOf(System.currentTimeMillis()));
        System.out.println(outpatientDetailsDao.insertDetails(details));
    }

    @Test
    public void selectDetails() {
        List<Details> detailsList = outpatientDetailsDao.selectDetails(null);
        for (Details details : detailsList) {
            System.out.println("details = " + details);
        }
    }
}