package com.tyk.demo;

import com.tyk.demo.service.ConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XmlToDbApplication {

    @Autowired
    private ConvertService convertServiceImpl;

    public static void main(String[] args) {
        SpringApplication.run(XmlToDbApplication.class, args);

    }
}
