package com.vintiduo.page;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by kostas on 2014.12.18.
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@org.springframework.stereotype.Component
@Scope("prototype")
public @interface PageResource {
}
