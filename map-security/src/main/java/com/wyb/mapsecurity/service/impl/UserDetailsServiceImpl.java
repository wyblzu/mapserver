package com.wyb.mapsecurity.service.impl;

import com.wyb.mapsecurity.dao.RoleMapper;
import com.wyb.mapsecurity.dao.UserMapper;
import com.wyb.mapsecurity.pojo.domain.Role;
import com.wyb.mapsecurity.pojo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户名权限
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-09-21 22:57
 **/
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    private final RoleMapper roleMapper;

    @Autowired
    public UserDetailsServiceImpl(UserMapper userMapper, RoleMapper roleMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        User user = this.userMapper.findUserByName(username);
        if (user != null) {
            List<Role> roles = this.roleMapper.findRoleByUserId(user.getId());
            user.setAuthorities(roles);
        }else {
            log.error("{}不存在，请检查输入用户名", username);
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }

}

