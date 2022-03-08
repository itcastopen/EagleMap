package cn.itcast.em.annotations;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EagleAutowired {

    boolean required() default true;

}