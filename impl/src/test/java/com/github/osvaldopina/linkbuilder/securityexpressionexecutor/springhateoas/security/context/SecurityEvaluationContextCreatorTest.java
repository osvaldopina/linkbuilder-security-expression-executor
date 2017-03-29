package com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.context;

import com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.SpringSecurityExpressionHandlerDiscover;
import org.easymock.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.FilterInvocation;

import static org.easymock.EasyMock.expect;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class SecurityEvaluationContextCreatorTest extends EasyMockSupport {

    @Rule
    public EasyMockRule mocks = new EasyMockRule(this);

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private SpringSecurityExpressionHandlerDiscover springSecurityExpressionHandlerDiscover;

    @Mock
    private SecurityExpressionHandler securityExpressionHandler;

    @Mock
    private DummyFilterInvocationCreator dummyFilterInvocationCreator;

    @Mock
    private EvaluationContext evaluationContext;

    @Mock
    private Authentication authentication;

    @Mock
    private FilterInvocation filterInvocation;

    @TestSubject
    private SecurityEvaluationContextCreator securityEvaluationContextCreator = new SecurityEvaluationContextCreator();

    @Before
    public void setUp() {
        SecurityContext securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void create() throws Exception {
        expect(springSecurityExpressionHandlerDiscover.recover(applicationContext)).
                andReturn(securityExpressionHandler);
        expect(dummyFilterInvocationCreator.create()).andReturn(filterInvocation);
        expect(securityExpressionHandler.createEvaluationContext(authentication, filterInvocation)).
                andReturn(evaluationContext);

        replayAll();

        assertThat(securityEvaluationContextCreator.create(applicationContext), is(sameInstance(evaluationContext)));

        verifyAll();

    }
}