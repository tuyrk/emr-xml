package com.tyk.demo.service.impl;

import com.tyk.demo.entity.hospitalization.Hospitalization;
import org.junit.Test;

import static org.junit.Assert.*;

public class Dom4jCreateXmlImplTest {

    @Test
    public void createHospitalization() {

    }

    @Test
    public void createOutpatient() {
    }

    @Test
    public void getClassName() {
        String className = Hospitalization.class.getName();
        System.out.println("Hospitalization.class.getName() = " + className.substring(0, className.lastIndexOf('.')));
    }
}