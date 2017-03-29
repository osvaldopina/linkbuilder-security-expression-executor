package com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security;


import com.github.osvaldopina.linkbuilder.expression.springhateoas.impl.BaseExpressionHandler;
import com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.context.SecurityEvaluationContextCreator;
import com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.parser.SecurityExpressionParser;
import com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.validityChecker.ValidityChecker;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;

public class LinkBuilderSecurityExpressionHandler extends BaseExpressionHandler {

	public static final LinkBuilderSecurityExpressionHandler INSTANCE = new LinkBuilderSecurityExpressionHandler();

	private SecurityExpressionParser securityExpressionParser = SecurityExpressionParser.INSTANCE;

	private SecurityEvaluationContextCreator securityEvaluationContextCreator = SecurityEvaluationContextCreator.INSTANCE;

	private ValidityChecker validityChecker = ValidityChecker.INSTANCE;

	public LinkBuilderSecurityExpressionHandler() {

	}


	@Override
	public EvaluationContext createEvalutationContext(ApplicationContext applicationContext) {

		return securityEvaluationContextCreator.create(applicationContext);
	}

	public Expression parse(ApplicationContext applicationContext, String expression) {

		return securityExpressionParser.parse(applicationContext, expression);

	}

	@Override
	public boolean isValid(ApplicationContext applicationContext) {

		return validityChecker.isValid(applicationContext);

	}

	@Override
	public int getPriority() {
		return 100;
	}
}