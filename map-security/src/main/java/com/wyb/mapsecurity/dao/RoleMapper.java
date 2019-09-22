package com.wyb.mapsecurity.dao;

import com.wyb.mapsecurity.pojo.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-09-21 22:12
 **/
@Repository
@Mapper
public interface RoleMapper {

    /**
     *
     * 根据用户Id查找用户对应角色
     *
     * @author wangyongbing
     * @date 2019/9/21 22:24
     *
     * @param userId 用户Id
     *
     * @return {@link List<Role>}
     *
     */
    @Select("SELECT A.id, A.name FROM role A LEFT JOIN user_role B ON A.id = B.role_id WHERE B.user_id = #{userId}")
    List<Role> findRoleByUserId(@Param("userId") Long userId);
}
