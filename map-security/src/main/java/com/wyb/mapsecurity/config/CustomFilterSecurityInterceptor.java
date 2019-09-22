package com.wyb.mapsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 受保护对象的访问进行拦截
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-09-21 23:57
 **/
@Component
public class CustomFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    private final FilterInvocationSecurityMetadataSource securityMetadataSource;


    @Autowired
    public void setMyAccessDecisionManager(CustomAccessDecisionManager customAccessDecisionManager) {
        super.setAccessDecisionManager(customAccessDecisionManager);
    }

    @Autowired
    public CustomFilterSecurityInterceptor(FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        FilterInvocation filterInvocation = new FilterInvocation(servletRequest, servletResponse, filterChain);
        invoke(filterInvocation);

    }

    private void invoke(FilterInvocation filterInvocation) throws IOException, ServletException {

        InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
        try {
            //执行下一个拦截器
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {

        return this.securityMetadataSource;
    }
}

