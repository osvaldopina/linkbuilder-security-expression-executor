package com.github.osvaldopina.linkbuilder.securityexpressionexecutor.springhateoas.security;

import com.github.osvaldopina.linkbuilder.LinkBuilderException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

public class SpringSecurityExpressionHandlerDiscover {

    public static final SpringSecurityExpressionHandlerDiscover INSTANCE =
            new SpringSecurityExpressionHandlerDiscover();

    protected SpringSecurityExpressionHandlerDiscover() {

    }

    public SecurityExpressionHandler<FilterInvocation> recover(ApplicationContext applicationContext) {
        try {
            return applicationContext.getBean(SecurityExpressionHandler.class);
        }
        catch(BeansException e) {
            throw new LinkBuilderException(
                    "A expression expression was configured but a instance of " +
                            DefaultWebSecurityExpressionHandler.class +
                            " could not be found because " +e + ". Is expression configured?"
            );
        }
    }

    public boolean hasInContext(ApplicationContext applicationContext) {
        try {
            applicationContext.getBean(SecurityExpressionHandler.class);
            return true;
        }
        catch(BeansException e) {
            return false;
        }
    }


}
