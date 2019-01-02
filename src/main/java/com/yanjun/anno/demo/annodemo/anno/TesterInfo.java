package com.yanjun.anno.demo.annodemo.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TesterInfo {

    enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    Priority priority() default Priority.MEDIUM;

    String[] tags() default "";

    String author() default "Junsr.";

    String createDate() default "";

    String lastModified() default "";
}
