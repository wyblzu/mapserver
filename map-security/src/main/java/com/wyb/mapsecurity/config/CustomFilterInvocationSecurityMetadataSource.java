package com.wyb.mapsecurity.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wyb.mapsecurity.dao.PermissionMapper;
import com.wyb.mapsecurity.pojo.domain.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * 请求与权限对应关系
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-09-21 23:09
 **/
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {


    private static HashMap<String, Collection<ConfigAttribute>> map = null;

    private final PermissionMapper permissionMapper;

    @Autowired
    public CustomFilterInvocationSecurityMetadataSource(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (map == null) {
            loadResourceDefine();
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        for (String url : map.keySet()) {
            if (new AntPathRequestMatcher(url).matches(request)) {
                return map.get(url);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }



    private void loadResourceDefine() {
        map = Maps.newHashMap();
        String url;
        String roleName;
        ConfigAttribute role;
        List<RolePermission> rolePermissions = this.permissionMapper.findRolePermission();
        for (RolePermission rolePermission : rolePermissions) {
            url = rolePermission.getUrl();
            roleName = rolePermission.getRoleName();
            role = new SecurityConfig(roleName);
            if (map.containsKey(url)) {
                map.get(url).add(role);
            }else {
                List<ConfigAttribute> list = Lists.newArrayList();
                list.add(role);
                map.put(url, list);
            }
        }
    }
}

