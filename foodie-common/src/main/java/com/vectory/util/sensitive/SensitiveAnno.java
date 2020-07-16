package com.vectory.util.sensitive;

import lombok.Data;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SensitiveAnno {

    SensitiveType type() ;
}
