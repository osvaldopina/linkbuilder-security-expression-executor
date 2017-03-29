package com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.context;

import javax.servlet.FilterChain;

import org.springframework.security.web.FilterInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class DummyFilterInvocationCreator {

    public static final DummyFilterInvocationCreator INSTANCE = new DummyFilterInvocationCreator();

    protected DummyFilterInvocationCreator() {

    }

    public FilterInvocation create() {

        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        FilterChain filterChain = new DummyFilterChain();

        return new FilterInvocation(
                servletRequestAttributes.getRequest(),
                servletRequestAttributes.getResponse(),
                filterChain
        );

    }
}
