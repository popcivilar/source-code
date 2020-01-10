package com.popcivilar.code.spring.mockaop;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Import(AopImportSelector.class)
public @interface EnableMockAop {
}
