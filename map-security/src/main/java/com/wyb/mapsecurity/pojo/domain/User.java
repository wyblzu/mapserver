package com.wyb.mapsecurity.pojo.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.List;

/**
 * 用户表
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-09-17 23:11
 **/
public class User implements UserDetails, Serializable {

    @Getter
    @Setter
    private Long id;

    @Setter
    private String username;

    @Setter
    private String password;

    @Setter
    List<Role> authorities;

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public List<Role> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

