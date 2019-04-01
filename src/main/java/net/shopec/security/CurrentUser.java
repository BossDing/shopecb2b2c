package net.shopec.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Security - 当前用户注解
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.PARAMETER)
public @interface CurrentUser {

}