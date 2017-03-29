package com.github.osvaldopina.linkbuilder.securityexpressionexecutor.configuration;

import com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security.LinkBuilderSecurityExpressionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LinkBuilderSecurityExpressionExecutorAutoConfiguration {

	@Bean
	public LinkBuilderSecurityExpressionHandler securityExpressionHandler() {
		return new LinkBuilderSecurityExpressionHandler();
	}

}
