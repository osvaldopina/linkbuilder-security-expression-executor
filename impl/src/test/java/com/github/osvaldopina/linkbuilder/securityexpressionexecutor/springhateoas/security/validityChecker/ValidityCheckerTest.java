package com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.validityChecker;

import static org.easymock.EasyMock.expect;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.SpringSecurityExpressionHandlerDiscover;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class ValidityCheckerTest  extends EasyMockSupport {

	@Rule
	public EasyMockRule mocks = new EasyMockRule(this);

	@Mock
	private SpringSecurityExpressionHandlerDiscover springSecurityExpressionHandlerDiscover;

	@Mock
	private Authentication authentication;

	@Mock
	private SecurityContext securityContext;

	@Mock
	private ApplicationContext applicationContext;


	@TestSubject
	private ValidityChecker validityChecker = new ValidityChecker();


	@Test
	public void isValid() {
		SecurityContextHolder.setContext(securityContext);
		expect(securityContext.getAuthentication()).andReturn(authentication);
		expect(springSecurityExpressionHandlerDiscover.hasInContext(applicationContext)).andReturn(true);

		replayAll();

		assertThat(validityChecker.isValid(applicationContext), is(true));

		verifyAll();
	}

	@Test
	public void isValid_securityContextNull() {
		SecurityContextHolder.clearContext();;

		replayAll();

		assertThat(validityChecker.isValid(applicationContext), is(false));

		verifyAll();
	}

	@Test
	public void isValid_autenticationIsNull() {
		SecurityContextHolder.setContext(securityContext);
		expect(securityContext.getAuthentication()).andReturn(null);

		replayAll();

		assertThat(validityChecker.isValid(applicationContext), is(false));

		verifyAll();
	}

	@Test
	public void isValid_hasInContextFalse() {
		SecurityContextHolder.setContext(securityContext);
		expect(securityContext.getAuthentication()).andReturn(authentication);
		expect(springSecurityExpressionHandlerDiscover.hasInContext(applicationContext)).andReturn(false);

		replayAll();

		assertThat(validityChecker.isValid(applicationContext), is(false));

		verifyAll();
	}
}