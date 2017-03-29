package com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.parser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.SpringSecurityExpressionHandlerDiscover;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.security.access.expression.SecurityExpressionHandler;

public class SecurityExpressionParser {

    public static final SecurityExpressionParser INSTANCE = new SecurityExpressionParser();

    SpringSecurityExpressionHandlerDiscover springSecurityExpressionHandlerDiscover =
            SpringSecurityExpressionHandlerDiscover.INSTANCE;

    SecurityExpressionParser() {

    }

    private Map<String,Expression> expressionCache = new ConcurrentHashMap<String, Expression>();

    public Expression parse(ApplicationContext applicationContext, String expression) {

        Expression parsedExpression = expressionCache.get(expression);

        if (parsedExpression == null) {

            SecurityExpressionHandler handler = springSecurityExpressionHandlerDiscover.
                    recover(applicationContext);

            parsedExpression = handler.getExpressionParser().parseExpression(expression);
            expressionCache.put(expression, parsedExpression);

            return  parsedExpression;
        }

        return parsedExpression;
    }

}
