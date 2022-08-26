package com.leo.base.dagger.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/5 15:26
 * Desc:
 */
@Scope
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AppScope {
}
