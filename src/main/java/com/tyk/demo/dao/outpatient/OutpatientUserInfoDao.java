package com.tyk.demo.dao.outpatient;

import com.tyk.demo.entity.outpatient.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/5/8 15:52 星期二
 * Description:
 */
@Mapper
@Component
public interface OutpatientUserInfoDao {
    int insertUserInfo(UserInfo userInfo);

    int updateUserInfo(UserInfo userInfo);

    List<UserInfo> selectUserInfo(@Param("id") String id);
}
