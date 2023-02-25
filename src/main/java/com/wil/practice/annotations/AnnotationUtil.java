package com.wil.practice.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AnnotationUtil {
    public String comment();
    public Params[] params() default {};

}
