package com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.parser;

import static org.easymock.EasyMock.expect;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.SpringSecurityExpressionHandlerDiscover;
import org.easymock.*;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.security.access.expression.SecurityExpressionHandler;

import java.util.Map;

public class SecurityExpressionParserTest extends EasyMockSupport {

    @Rule
    public EasyMockRule mocks = new EasyMockRule(this);

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private SpringSecurityExpressionHandlerDiscover springSecurityExpressionHandlerDiscover;

    @Mock
    private SecurityExpressionHandler securityExpressionHandler;

    @Mock
    private Map<String,Expression> cache;

    @Mock
    private ExpressionParser expressionParser;

    @Mock
    private Expression parsedExpression;

    @TestSubject
    private SecurityExpressionParser securityExpressionParser = new SecurityExpressionParser();

    @Test
    public void getExpressionForNonCachedExpression() throws Exception {

        String expression = "expression expression";

        expect(cache.get(expression)).andReturn(null);
        expect(springSecurityExpressionHandlerDiscover.recover(applicationContext)).andReturn(securityExpressionHandler);
        expect(securityExpressionHandler.getExpressionParser()).andReturn(expressionParser);
        expect(expressionParser.parseExpression(expression)).andReturn(parsedExpression);
        expect(cache.put(expression, parsedExpression)).andReturn(parsedExpression);

        replayAll();

        assertThat(securityExpressionParser.parse(applicationContext, expression), is(sameInstance(parsedExpression)));

        verifyAll();

    }

    @Test
    public void getExpressionForCachedExpression() throws Exception {

        String expression = "expression expression";

        expect(cache.get(expression)).andReturn(parsedExpression);

        replayAll();

        assertThat(securityExpressionParser.parse(applicationContext, expression), is(sameInstance(parsedExpression)));

        verifyAll();

    }

}