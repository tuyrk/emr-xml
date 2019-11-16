package com.tyk.demo.service;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/5/8 22:37 星期二
 * Description:
 */
public interface ConvertService {
    boolean xml2db(String filename);

    boolean db2xml(String filename);
}
