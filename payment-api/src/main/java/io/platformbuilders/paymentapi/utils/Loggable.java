/*
* Copyright 2018 V-Platform
*************************************************************
*Nome     : Loggable.java
*Autor    : Builders
*Data     : 05/09/2018
*Empresa  : Platform Builders
*************************************************************
*/
package io.platformbuilders.paymentapi.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {
}
