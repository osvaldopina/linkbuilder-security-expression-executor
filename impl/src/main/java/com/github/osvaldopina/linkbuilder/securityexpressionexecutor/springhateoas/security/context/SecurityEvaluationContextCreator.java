package com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.context;

import com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.SpringSecurityExpressionHandlerDiscover;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityEvaluationContextCreator {

	public static final SecurityEvaluationContextCreator INSTANCE = new SecurityEvaluationContextCreator();

	private DummyFilterInvocationCreator dummyFilterInvocationCreator = DummyFilterInvocationCreator.INSTANCE;

	private SpringSecurityExpressionHandlerDiscover springSecurityExpressionHandlerDiscover =
			SpringSecurityExpressionHandlerDiscover.INSTANCE;

	protected SecurityEvaluationContextCreator() {

	}

	public EvaluationContext create(ApplicationContext applicationContext) {

		SecurityExpressionHandler handler = springSecurityExpressionHandlerDiscover.
				recover(applicationContext);

		return handler.createEvaluationContext(SecurityContextHolder.getContext().getAuthentication(),
				dummyFilterInvocationCreator.create());
	}



}
