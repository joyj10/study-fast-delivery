package org.delivery.api.comon.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Business
 * - 해당 애노테이션 비즈니스 애노테이션(당 애노테이션 붙으면 비즈니스 로직이 있다는 것을 명시하는 목적)
 * - @Service으로 인해 서비스 처럼 스프링이 빈으로 등록됨
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service    //@Component 적어도 됨
public @interface Business {
    @AliasFor(annotation = Service.class)
    String value() default "";
}
