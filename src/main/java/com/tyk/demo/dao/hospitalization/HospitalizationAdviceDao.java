package com.tyk.demo.dao.hospitalization;

import com.tyk.demo.entity.hospitalization.Advice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/5/8 14:23 星期二
 * Description:
 */
@Mapper
@Component
public interface HospitalizationAdviceDao {
    int insertAdvice(Advice advice);

    int updateAdvice(Advice advice);

    List<Advice> selectAdvice(@Param("id") String id);
}
