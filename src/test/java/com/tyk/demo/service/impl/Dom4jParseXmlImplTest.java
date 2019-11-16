package com.tyk.demo.service.impl;

import com.tyk.demo.XmlToDbApplicationTests;
import com.tyk.demo.service.Dom4jParseXml;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Dom4jParseXmlImplTest extends XmlToDbApplicationTests {

    @Autowired
    private Dom4jParseXml dom4jParseXml;

    @Test
    public void getHospitalizationList() {
        String filename = "xml/hospitalization.xml";
        ArrayList hospitalizationList = dom4jParseXml.getHospitalizationList(filename);
        System.out.println("hospitalizationList = " + hospitalizationList);
    }

    @Test
    public void getOutpatientList() {
        String filename = "xml/outpatient.xml";
        ArrayList outpatientList = dom4jParseXml.getOutpatientList(filename);
        System.out.println("outpatientList = " + outpatientList);
    }
}