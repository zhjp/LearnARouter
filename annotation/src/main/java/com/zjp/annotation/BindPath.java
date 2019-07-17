package com.zjp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)  //声明注解的作用域    是类上
@Retention(RetentionPolicy.CLASS)    //声明注解的生命周期    源码 java  编译期  class  运行期  jdex
public @interface BindPath {
    String value();
}
