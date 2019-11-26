package com.zben.shop.member.mapper;

import com.zben.shop.common.mybatis.BaseDao;
import com.zben.shop.member.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @DESC:
 * @AUTHOR: zhouben
 * @DATE: 2019/11/22 0022 20:23
 */
@Mapper
public interface UserMapper extends BaseDao {

    @Select("select * from mb_user where phone=#{phone} and password=#{password}")
    public UserEntity findByPhoneAndPwd(@Param("phone") String phone, @Param("password") String password);

    @Select("select * from mb_user where id=#{id}")
    UserEntity findById(@Param("id") String id);
}
