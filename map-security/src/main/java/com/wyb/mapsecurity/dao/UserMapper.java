package com.wyb.mapsecurity.dao;

import com.wyb.mapsecurity.pojo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 用户
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-09-21 22:12
 **/
@Repository
@Mapper
public interface UserMapper {


    /**
     *
     * 根据用户名查询用户
     *
     * @author wangyongbing
     * @date 2019/9/21 22:14
     *
     * @param username 用户名
     *
     * @return {@link User}
     *
     */
    @Select("SELECT id, username, password FROM \"user\" WHERE username = #{username}")
    User findUserByName(@Param("username") String username);

}

