package com.biye.rws.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface WsController {

    @AliasFor("path")
    String value() default "/";

    @AliasFor("value")
    String path() default "/";
}
