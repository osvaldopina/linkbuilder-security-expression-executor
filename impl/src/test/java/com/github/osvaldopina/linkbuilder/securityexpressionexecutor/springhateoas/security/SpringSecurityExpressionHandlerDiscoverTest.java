package com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security;

import com.github.osvaldopina.linkbuilder.LinkBuilderException;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import static org.easymock.EasyMock.expect;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class SpringSecurityExpressionHandlerDiscoverTest extends EasyMockSupport {

    @Rule
    public EasyMockRule mocks = new EasyMockRule(this);

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private SecurityExpressionHandler<FilterInvocation> securityExpressionHandler;

    @Mock
    private SpringSecurityExpressionHandlerDiscover springSecurityExpressionHandlerDiscover;

    @Before
    public void setUp() {
        springSecurityExpressionHandlerDiscover = new SpringSecurityExpressionHandlerDiscover();

    }

    @Test
    public void recover() throws Exception {

        expect(applicationContext.getBean(SecurityExpressionHandler.class)).
                andReturn(securityExpressionHandler);


        replayAll();

        assertThat(springSecurityExpressionHandlerDiscover.recover(applicationContext),
                is(Matchers.sameInstance(securityExpressionHandler)));

        verifyAll();
    }

    @Test
    public void hasInContext() throws Exception {
        expect(applicationContext.getBean(SecurityExpressionHandler.class)).andReturn(securityExpressionHandler);

        replayAll();

        assertThat(springSecurityExpressionHandlerDiscover.hasInContext(applicationContext), is(true));

        verifyAll();

    }

    @Test
    public void hasInContextBeanNotFound() throws Exception {
        expect(applicationContext.getBean(SecurityExpressionHandler.class)).andThrow(new BeansException("message") {
            @Override
            public boolean equals(Object other) {
                return super.equals(other);
            }
        });

        replayAll();

        assertThat(springSecurityExpressionHandlerDiscover.hasInContext(applicationContext), is(false));

        verifyAll();

    }
}