package com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security;

import static org.easymock.EasyMock.expect;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.context.SecurityEvaluationContextCreator;
import com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.parser.SecurityExpressionParser;
import com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.validityChecker.ValidityChecker;
import org.easymock.*;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import javax.servlet.http.HttpServletRequest;

public class LinkBuilderSecurityExpressionHandlerTest extends EasyMockSupport {

    @Rule
    public EasyMockRule mocks = new EasyMockRule(this);

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private Expression parsedExpression;

    @Mock
    private SecurityExpressionParser securityExpressionParser;

    @Mock
    private SecurityEvaluationContextCreator securityEvaluationContextCreator;

    @Mock
    private EvaluationContext evaluationContext;

    @Mock
    private ValidityChecker validityChecker;

    @Mock
    private HttpServletRequest request;

    private DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler =
            new DefaultWebSecurityExpressionHandler();

    @TestSubject
    private LinkBuilderSecurityExpressionHandler linkBuilderSecurityExpressionHandler
            = new LinkBuilderSecurityExpressionHandler();

    @Test
    public void createEvaluationContext() throws Exception {

        expect(securityEvaluationContextCreator.create(applicationContext)).andReturn(evaluationContext);

        replayAll();

        assertThat(linkBuilderSecurityExpressionHandler.createEvalutationContext(applicationContext),
                is(sameInstance(evaluationContext)));

        verifyAll();

    }

    @Test
    public void parse() throws Exception {
        String expression = "expression expression";

        expect(securityExpressionParser.parse(applicationContext,expression)).andReturn(parsedExpression);

        replayAll();

        assertThat(linkBuilderSecurityExpressionHandler.parse(applicationContext, expression), is(parsedExpression));

        verifyAll();

    }

    @Test
    public void isValid() throws Exception {

        expect(validityChecker.isValid(applicationContext)).andReturn(true);

        replayAll();

        assertThat(linkBuilderSecurityExpressionHandler.isValid(applicationContext), is(true));

        verifyAll();

    }

}