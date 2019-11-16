package com.tyk.demo.service.impl;

import com.tyk.demo.XmlToDbApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ConvertServiceImplTest extends XmlToDbApplicationTests {

    @Autowired
    private ConvertServiceImpl convertServiceImpl;

    @Test
    public void main() {
        xml2db();
        db2xml();
    }

    @Test
    public void xml2db() {
        String filename = "xml/hospitalization.xml";
        convertServiceImpl.xml2db(filename);
        filename = "xml/outpatient.xml";
        convertServiceImpl.xml2db(filename);
    }

    @Test
    public void db2xml() {
        String filename = "xml/out/hospitalization.xml";
        convertServiceImpl.db2xml(filename);
        filename = "xml/out/outpatient.xml";
        convertServiceImpl.db2xml(filename);
    }
}