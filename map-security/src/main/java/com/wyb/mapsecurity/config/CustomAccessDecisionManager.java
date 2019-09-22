package com.wyb.mapsecurity.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 决策器
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-09-18 23:07
 **/
@Component
@Slf4j
public class CustomAccessDecisionManager implements AccessDecisionManager {


    /**
     * 请输入方法描述
     *
     * @param authentication   包含了当前的用户信息，包括拥有的权限。这里的权限来源就是前面登录时UserDetailsService中设置的authorities
     * @param object           就是FilterInvocation对象，可以得到request等web资源
     * @param configAttributes 是本次访问需要的权限
     * @throws AccessDeniedException 当前访问没有权限
     * @author wangyongbing
     * @date 2019/9/18 23:20
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes == null || configAttributes.size() <= 0) {
            return;
        } else {
            String needRole;
            for (ConfigAttribute configAttribute : configAttributes) {
                needRole = configAttribute.getAttribute();
                for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                    if (needRole.trim().equalsIgnoreCase(grantedAuthority.getAuthority().trim())) {
                        return;
                    }
                }
            }
            throw new AccessDeniedException("当前访问没有权限");
        }
    }

    /**
     * 请表示此AccessDecisionManager是否能够处理传递的ConfigAttribute呈现的授权请求
     *
     * @author wangyongbing
     * @date 2019/9/18 23:22
     */

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    /**
     * 表示当前AccessDecisionManager实现是否能够为指定的安全对象（方法调用或Web请求）提供访问控制决策
     *
     * @param clazz
     * @return boolean
     * @author wangyongbing
     * @date 2019/9/18 23:23
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }


}

