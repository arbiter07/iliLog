package com.ililog.api.config;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = IliLogMockSecurityContext.class)
public @interface IliLogMockUser {

    String name() default "ILI";

    String email() default "abcd22@gmail.com";

    String password() default "";

//    String role() default "ROLE_ADMIN";
}
