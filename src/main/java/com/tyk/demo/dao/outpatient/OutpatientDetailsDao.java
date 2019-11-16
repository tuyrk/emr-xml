package com.tyk.demo.dao.outpatient;

import com.tyk.demo.entity.outpatient.Details;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/5/8 15:53 星期二
 * Description:
 */
@Mapper
@Component
public interface OutpatientDetailsDao {
    int insertDetails(Details Details);

    int updateDetails(Details details);

    List<Details> selectDetails(@Param("id") String id);
}
