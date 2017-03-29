package com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.validityChecker;

import com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.SpringSecurityExpressionHandlerDiscover;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class ValidityChecker {

	public static final ValidityChecker INSTANCE = new ValidityChecker();


	private SpringSecurityExpressionHandlerDiscover springSecurityExpressionHandlerDiscover =
			SpringSecurityExpressionHandlerDiscover.INSTANCE;

	protected ValidityChecker() {

	}

	public boolean isValid(ApplicationContext applicationContext) {
		return SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null &&
				springSecurityExpressionHandlerDiscover.hasInContext(applicationContext);
	}

}
