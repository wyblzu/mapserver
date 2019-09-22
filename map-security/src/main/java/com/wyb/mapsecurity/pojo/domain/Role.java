package com.wyb.mapsecurity.pojo.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * 角色
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-09-17 23:16
 **/
@Data
public class Role implements GrantedAuthority {

    private Long id;

    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}

