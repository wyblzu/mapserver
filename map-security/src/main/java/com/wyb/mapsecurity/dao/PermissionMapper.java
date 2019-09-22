package com.wyb.mapsecurity.dao;

import com.wyb.mapsecurity.pojo.domain.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色对应的url
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-09-21 22:43
 **/
@Repository
@Mapper
public interface PermissionMapper {


    /**
     * 角色对应的权限
     *
     * @return {@link List<RolePermission>}
     * @author wangyongbing
     * @date 2019/9/21 22:51
     */
    @Select("SELECT A.NAME AS roleName,C.url FROM role AS A LEFT JOIN role_permission AS B ON A.id = B.role_id LEFT JOIN permission AS C ON B.permission_id = C.id")
    List<RolePermission> findRolePermission();


}

