# 基于Java的XML电子病历导入导出数据库  
## 开发环境：  
* 编程序言：Java
* 数据库：MySQL5.7
* 编译器：Intellij Idea
## 电子病历模板  
如图：

![中医住院电子病历](https://gitee.com/tuyuankun/emr_xml/raw/master/doc/%E4%B8%AD%E5%8C%BB%E4%BD%8F%E9%99%A2%E7%94%B5%E5%AD%90%E7%97%85%E5%8E%86.png)
![门诊电子病历明细](https://gitee.com/tuyuankun/emr_xml/raw/master/doc/%E9%97%A8%E8%AF%8A%E7%94%B5%E5%AD%90%E7%97%85%E5%8E%86%E6%98%8E%E7%BB%86.jpg)
## 步骤  

1.建立Java项目，添加依赖
---
```
<!-- 数据库连接 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
<!-- Mybatis -->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.3.2</version>
</dependency>
<!-- XML解析 -->
<dependency>
    <groupId>dom4j</groupId>
    <artifactId>dom4j</artifactId>
    <version>1.6.1</version>
</dependency>
```
2.编写XML文件
---
>中医住院电子病历
```xml
<?xml version="1.0" encoding="UTF-8"?>

<Hospitalizations namespace="com.tyk.demo.entity.hospitalization">
    <Hospitalization>
        <Hospital>
            <Id>001</Id>
            <Hospital>成都中医药大学附属医院</Hospital>
        </Hospital>
        <UserInfo>
            <Name>涂元坤</Name>
            <Sex>男</Sex>
            <Birth>1996-11-11</Birth>
            <Age>21</Age>
            <Position>学生</Position>
            <Phone>18382471393</Phone>
            <Count>0</Count>
            <Marriage>未婚</Marriage>
            <Nation>汉族</Nation>
            <IdNumber>510823199611114410</IdNumber>
            <Domicile>成都</Domicile>
            <Homeplace>广元</Homeplace>
        </UserInfo>
        <Details>
            <Hospital>成都中医药大学附属医院</Hospital>
            <Route>网上预约</Route>
            <Type>轻度</Type>
            <InTime>2018-05-07 09:00</InTime>
            <OutTime>2018-05-08 09:00</OutTime>
            <InCategory>内科</InCategory>
            <OutCategory>内科</OutCategory>
            <TransCategory>无</TransCategory>
            <Day>1</Day>
            <Diagnosis>无</Diagnosis>
            <FourDiagnosis>无</FourDiagnosis>
            <SyndromeType>无</SyndromeType>
            <Treatment>无</Treatment>
            <DiseaseBit>无</DiseaseBit>
            <Disease>无</Disease>
            <Chinese>无</Chinese>
            <Western>无</Western>
            <Clinical>无</Clinical>
        </Details>
        <Advice>
            <Director>赵</Director>
            <Deputy>钱</Deputy>
            <Attending>孙</Attending>
            <Advanced>李</Advanced>
            <Inpatient>周</Inpatient>
            <Nurse>吴</Nurse>
            <Records>无</Records>
            <Time>2018-05-08 09:00</Time>
            <Route>无</Route>
        </Advice>
    </Hospitalization>
</Hospitalizations>

```
>门诊电子病历明细
```xml
<?xml version="1.0" encoding="UTF-8"?>

<Outpatients namespace="com.tyk.demo.entity.outpatient">
    <Outpatient id="001">
        <UserInfo>
            <Name>涂元坤</Name>
            <Sex>男</Sex>
            <Age>21</Age>
            <Phone>18382471393</Phone>
            <IdNumber>510823199611114410</IdNumber>
            <Address>成都中医药大学</Address>
        </UserInfo>
        <Details>
            <Department>门诊医生</Department>
            <Time>2017-09-09 03:40</Time>
            <FourDiagnosis>咳嗽，呼吸声粗，痰量多，咳痰黄稠，不易咯痰，红舌，黄苔，滑数脉，气味腥臭</FourDiagnosis>
            <Diagnosis>痰热壅肺证</Diagnosis>
            <SyndromeType>痰热壅肺证</SyndromeType>
            <Allergy>无</Allergy>
            <Treatment>清热化痰，活血</Treatment>
            <DiseaseBit>肺，心，胆</DiseaseBit>
            <Disease>咳嗽</Disease>
            <Chinese>宣降止咳汤 地骨皮15.00g 五味子12.00g 白芍15.00g 款冬花12.00g 细辛5.00g 黄芪15.00g 苏子15.00g 灸麻黄12.00g 桂枝10.00g 干姜10.00g</Chinese>
        </Details>
    </Outpatient>
</Outpatients>
```
3.实体类编写
---
>实体类非常简单直接查看实体类代码。  

4.建立并连接数据库
---
>SQL建库脚本
```sql
CREATE DATABASE IF NOT EXISTS `emr_xml`;
USE `emr_xml`;
SET NAMES utf8;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `hospitalization.advice`;
CREATE TABLE `hospitalization.advice` (
  `id`        VARCHAR(20) COLLATE utf8_unicode_ci NOT NULL,
  `director`  VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `deputy`    VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `attending` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `advanced`  VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `inpatient` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nurse`     VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `records`   VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time`      DATETIME                            DEFAULT NULL,
  `route`     VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

DROP TABLE IF EXISTS `hospitalization.details`;
CREATE TABLE `hospitalization.details` (
  `id`            VARCHAR(20) COLLATE utf8_unicode_ci NOT NULL,
  `hospital`      VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `route`         VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type`          VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `inTime`        DATETIME                            DEFAULT NULL,
  `outTime`       DATETIME                            DEFAULT NULL,
  `inCategory`    VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `outCategory`   VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `transCategory` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `day`           INT(11)                             DEFAULT NULL,
  `diagnosis`     VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fourDiagnosis` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `syndromeType`  VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `treatment`     VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `diseaseBit`    VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `disease`       VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `chinese`       VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `western`       VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clinical`      VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

DROP TABLE IF EXISTS `hospitalization.userinfo`;
CREATE TABLE `hospitalization.userinfo` (
  `id`        VARCHAR(20) COLLATE utf8_unicode_ci NOT NULL,
  `name`      VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex`       VARCHAR(1) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `birth`     DATETIME                            DEFAULT NULL,
  `age`       INT(11)                             DEFAULT NULL,
  `position`  VARCHAR(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone`     VARCHAR(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `count`     INT(11)                             DEFAULT NULL,
  `marriage`  VARCHAR(3) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `nation`    VARCHAR(3) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `idNumber`  VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `domicile`  VARCHAR(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `homeplace` VARCHAR(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

DROP TABLE IF EXISTS `outpatient.details`;
CREATE TABLE `outpatient.details` (
  `id`            VARCHAR(20) COLLATE utf8_unicode_ci NOT NULL,
  `department`    VARCHAR(20) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `time`          DATETIME                             DEFAULT NULL,
  `fourDiagnosis` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `diagnosis`     VARCHAR(20) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `syndromeType`  VARCHAR(20) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `allergy`       VARCHAR(20) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `treatment`     VARCHAR(20) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `diseaseBit`    VARCHAR(20) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `disease`       VARCHAR(20) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `chinese`       VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

DROP TABLE IF EXISTS `outpatient.userinfo`;
CREATE TABLE `outpatient.userinfo` (
  `id`       VARCHAR(20) COLLATE utf8_unicode_ci NOT NULL,
  `name`     VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex`      VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `age`      INT(11)                             DEFAULT NULL,
  `phone`    VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idNumber` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address`  VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
```
>连接数据库
```yaml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/emr_xml?characterEncoding=utf-8&useSSL=false&allowMultiQueries=true
    username: root
    password: 123456
```
5.Dao层编写
---
>编写Interface接口  
>编写mapper  
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="">
</mapper>
```
>配置mapper文件位置
```yaml
mybatis:
  mapper-locations: classpath:mapping/*/*.xml
```
6.**Service层编写**
---
**此部分为重点部分**  
>XML导入数据库  

1.编写解析XML的```Dom4jParseXmlImpl.java```类
```java
public class Dom4jParseXmlImpl implements Dom4jParseXml {
    /**
    * 中医住院电子病历
    */
    public ArrayList<Hospitalization> getHospitalizationList(String filename) {
        ArrayList<Hospitalization> hospitalizationArrayList = new ArrayList<>();
        
        // Dom4j解析xml
        // 创建的对象reader
        SAXReader reader = new SAXReader();

        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            // 通过reader对象的read方法加载xml文件，获取document对象
            Document document = reader.read(fileInputStream);
            // 通过document对象获取根节点
            Element hospitalizationsElement = document.getRootElement();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = hospitalizationsElement.elementIterator();
            // 全局变量记录第几条数据
            // 遍历迭代器，获取根节点中的每一条数据
            Hospitalization hospitalization;
            while (it.hasNext()) {
                hospitalization = new Hospitalization();
                Element hospitalizationElement = (Element) it.next();

                Hospital hospital = (Hospital) getObject(hospitalizationElement, new Hospital());
                UserInfo userInfo = (UserInfo) getObject(hospitalizationElement, new UserInfo());
                Details details = (Details) getObject(hospitalizationElement, new Details());
                Advice advice = (Advice) getObject(hospitalizationElement, new Advice());
                userInfo.setId(hospital.getId());
                details.setId(hospital.getId());
                details.setHospital(hospital.getHospital());
                advice.setId(hospital.getId());
                hospitalization.setUserInfo(userInfo);
                hospitalization.setDetails(details);
                hospitalization.setAdvice(advice);
                hospitalizationArrayList.add(hospitalization);
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return hospitalizationArrayList;
    }
    /**
    * 门诊电子病历明细
    */
    public ArrayList<Outpatient> getOutpatientList(String filename) {
        ArrayList<Outpatient> outpatientArrayList = new ArrayList<>();

        // Dom4j解析xml
        // 创建的对象reader
        SAXReader reader = new SAXReader();

        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            // 通过reader对象的read方法加载xml文件，获取document对象
            Document document = reader.read(fileInputStream);
            // 通过document对象获取根节点
            Element outpatientsElement = document.getRootElement();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = outpatientsElement.elementIterator();
            // 全局变量记录第几条数据
            // 遍历迭代器，获取根节点中的每一条数据
            while (it.hasNext()) {
                Outpatient outpatient = new Outpatient();
                Element outpatientElement = (Element) it.next();
                String id = outpatientElement.attribute("id").getStringValue();
                com.tyk.demo.entity.outpatient.UserInfo userInfo = (com.tyk.demo.entity.outpatient.UserInfo) getObject(outpatientElement, new com.tyk.demo.entity.outpatient.UserInfo());
                com.tyk.demo.entity.outpatient.Details details = (com.tyk.demo.entity.outpatient.Details) getObject(outpatientElement, new com.tyk.demo.entity.outpatient.Details());
                userInfo.setId(id);
                details.setId(id);
                outpatient.setUserInfo(userInfo);
                outpatient.setDetails(details);
                outpatientArrayList.add(outpatient);
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return outpatientArrayList;
    }

    private Object getObject(Element ObjectElement, Object obj) {
        Element objElement = ObjectElement.element(obj.getClass().getSimpleName());
        String name = "";
        try {
            //通过反射获取Element对象中的每一个属性，然后Object赋值。
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                name = field.getName();
                if (name.equals("id") && (!obj.getClass().equals(Hospital.class))) {
                    continue;
                }
                if (!name.equals("serialVersionUID")) {
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    Method setM = obj.getClass().getMethod("set" + name, String.class);
                    setM.invoke(obj, objElement.element(name).getStringValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return obj;
    }
}
```
2.调用写好的```Dom4jParseXmlImpl.java```类将XML导入数据库
```java
public class ConvertServiceImpl implements ConvertService {

    @Autowired
    private Dom4jParseXml dom4jParseXml;
    @Autowired
    private HospitalizationUserInfoDao hospitalizationUserInfoDao;
    @Autowired
    private HospitalizationDetailsDao hospitalizationDetailsDao;
    @Autowired
    private HospitalizationAdviceDao hospitalizationAdviceDao;

    @Autowired
    private OutpatientUserInfoDao outpatientUserInfoDao;
    @Autowired
    private OutpatientDetailsDao outpatientDetailsDao;

    @Override
    @Transactional
    public boolean xml2db(String filename) {
        try {
            if (filename.contains("hospitalization")) {
                List<Hospitalization> hospitalizationList = dom4jParseXml.getHospitalizationList(filename);
                for (Hospitalization hospitalization : hospitalizationList) {
                    if (hospitalizationUserInfoDao.selectUserInfo(hospitalization.getUserInfo().getId()).size() == 0) {
                        log.info("【XML to DB】-hospitalization.xml->" + hospitalization);
                        hospitalizationUserInfoDao.insertUserInfo(hospitalization.getUserInfo());
                        hospitalizationDetailsDao.insertDetails(hospitalization.getDetails());
                        hospitalizationAdviceDao.insertAdvice(hospitalization.getAdvice());
                    } else {
                        log.info("【XML to DB】-" + hospitalization + "已经存在,进行更新");
                        hospitalizationUserInfoDao.updateUserInfo(hospitalization.getUserInfo());
                        hospitalizationDetailsDao.updateDetails(hospitalization.getDetails());
                        hospitalizationAdviceDao.updateAdvice(hospitalization.getAdvice());
                    }
                }
            } else if (filename.contains("outpatient")) {
                List<Outpatient> outpatientList = dom4jParseXml.getOutpatientList(filename);
                for (Outpatient outpatient : outpatientList) {
                    if (outpatientUserInfoDao.selectUserInfo(outpatient.getUserInfo().getId()).size() == 0) {
                        log.info("【XML to DB】-outpatient.xml->" + outpatient);
                        outpatientUserInfoDao.insertUserInfo(outpatient.getUserInfo());
                        outpatientDetailsDao.insertDetails(outpatient.getDetails());
                    } else {
                        log.info("【XML to DB】-" + outpatient + "已经存在,进行更新");
                        outpatientUserInfoDao.updateUserInfo(outpatient.getUserInfo());
                        outpatientDetailsDao.updateDetails(outpatient.getDetails());
                    }

                }
            } else {
                log.error("请标准命名xml文件{ \"hospitalization.xml\", \"outpatient\" }");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

```
3.导入测试
```java
public class ConvertServiceImplTest extends XmlToDbApplicationTests {
    @Autowired
    private ConvertServiceImpl convertServiceImpl;
    @Test
    public void xml2db() {
        String filename = "xml/hospitalization.xml";
        convertServiceImpl.xml2db(filename);
        filename = "xml/outpatient.xml";
        convertServiceImpl.xml2db(filename);
    }
}
```
>数据库导出XML  

1.编写解析XML的```Dom4jCreateXmlImpl.java```类
```java
public class Dom4jCreateXmlImpl implements Dom4jCreateXml {
    @Override
    public boolean createHospitalization(List<Hospitalization> hospitalizationList, String filename) {
        try {
            File out = new File(filename);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileOutputStream(out), format);
            writer.write(createHospitalizationDocument(hospitalizationList));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean createOutpatient(List<Outpatient> outpatientList, String filename) {
        try {
            File out = new File(filename);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileOutputStream(out), format);
            writer.write(createOutpatientDocument(outpatientList));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Document createHospitalizationDocument(List<Hospitalization> hospitalizationList) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement(Hospitalization.class.getSimpleName() + 's');
        String className = Hospitalization.class.getName();
        root.addAttribute("namespace", className.substring(0, className.lastIndexOf('.')));
        UserInfo userInfo;
        Details details;
        Hospital hospital;
        for (Hospitalization hospitalization : hospitalizationList) {
            Element hospitalizationElement = root.addElement(Hospitalization.class.getSimpleName());
            userInfo = hospitalization.getUserInfo();
            details = hospitalization.getDetails();
            Advice advice = hospitalization.getAdvice();
            hospital = new Hospital(details.getId(), details.getHospital());
            hospitalizationElement.add(getDocument(hospital));
            hospitalizationElement.add(getDocument(userInfo));
            hospitalizationElement.add(getDocument(details));
            hospitalizationElement.add(getDocument(advice));
        }
        return document;
    }

    private Document createOutpatientDocument(List<Outpatient> outpatientList) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement(Outpatient.class.getSimpleName() + 's');
        String className = Outpatient.class.getName();
        root.addAttribute("namespace", className.substring(0, className.lastIndexOf('.')));
        com.tyk.demo.entity.outpatient.UserInfo userInfo;
        com.tyk.demo.entity.outpatient.Details details;
        for (Outpatient outpatient : outpatientList) {
            Element outpatientElement = root.addElement(Outpatient.class.getSimpleName());
            userInfo = outpatient.getUserInfo();
            details = outpatient.getDetails();
            outpatientElement.addAttribute("id", userInfo.getId());
            outpatientElement.add(getDocument(userInfo));
            outpatientElement.add(getDocument(details));
        }
        return document;
    }

    private Element getDocument(Object obj) {
        Element root = DocumentHelper.createElement(obj.getClass().getSimpleName());
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                String name = field.getName();
                if (name.equals("id") && (!obj.getClass().getName().equals(Hospital.class.getName()))) {
                    continue;
                }
                if (!name.equals("serialVersionUID")) {
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    Method getM = obj.getClass().getMethod("get" + name);
                    Element node = root.addElement(name);
                    node.setText(String.valueOf(getM.invoke(obj)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return root;
    }
}
```
2.调用写好的```Dom4jCreateXmlImpl.java```类将数据库导出生成XML
```java
public class ConvertServiceImpl implements ConvertService {

    @Autowired
    private Dom4jCreateXml dom4jCreateXml;
    @Autowired
    private HospitalizationUserInfoDao hospitalizationUserInfoDao;
    @Autowired
    private HospitalizationDetailsDao hospitalizationDetailsDao;
    @Autowired
    private HospitalizationAdviceDao hospitalizationAdviceDao;

    @Autowired
    private OutpatientUserInfoDao outpatientUserInfoDao;
    @Autowired
    private OutpatientDetailsDao outpatientDetailsDao;

    @Override
    @Transactional
    public boolean db2xml(String filename) {
        try {
            if (filename.contains("hospitalization")) {
                List<UserInfo> userInfoList = hospitalizationUserInfoDao.selectUserInfo(null);
                List<Details> detailsList = hospitalizationDetailsDao.selectDetails(null);
                List<Advice> adviceList = hospitalizationAdviceDao.selectAdvice(null);
                int n = userInfoList.size();
                List<Hospitalization> hospitalizationList = new ArrayList<>();
                Hospitalization hospitalization;
                for (int i = 0; i < n; i++) {
                    hospitalization = new Hospitalization();
                    hospitalization.setUserInfo(userInfoList.get(i));
                    hospitalization.setDetails(detailsList.get(i));
                    hospitalization.setAdvice(adviceList.get(i));
                    hospitalizationList.add(hospitalization);
                    log.info("【DB to XML】-hospitalization.xml->" + hospitalization);
                }
                return dom4jCreateXml.createHospitalization(hospitalizationList, filename);
            } else if (filename.contains("outpatient")) {
                List<com.tyk.demo.entity.outpatient.UserInfo> userInfoList = outpatientUserInfoDao.selectUserInfo(null);
                List<com.tyk.demo.entity.outpatient.Details> detailsList = outpatientDetailsDao.selectDetails(null);
                int n = userInfoList.size();
                List<Outpatient> outpatientList = new ArrayList<>();
                Outpatient outpatient;
                for (int i = 0; i < n; i++) {
                    outpatient = new Outpatient();
                    outpatient.setUserInfo(userInfoList.get(i));
                    outpatient.setDetails(detailsList.get(i));
                    outpatientList.add(outpatient);
                    log.info("【DB to XML】-outpatient.xml->" + outpatient);
                }
                return dom4jCreateXml.createOutpatient(outpatientList, filename);
            } else {
                log.error("请标准命名xml文件{ \"hospitalization.xml\", \"outpatient\" }");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
```
3.导出测试
```java
public class ConvertServiceImplTest extends XmlToDbApplicationTests {
    @Autowired
    private ConvertServiceImpl convertServiceImpl;
    @Test
    public void db2xml() {
        String filename = "xml/out/hospitalization.xml";
        convertServiceImpl.db2xml(filename);
        filename = "xml/out/outpatient.xml";
        convertServiceImpl.db2xml(filename);
    }
}
```