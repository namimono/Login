package com.namimono.securitylogin.demo.config.utils;

import java.lang.annotation.*;

/**
 * Created by wuxiao on 2019/7/29 14:40.
 */
@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME )
public @interface MyAnnotation {
    String alias() default "";
    String name() default "";
    boolean isIgnore() default false;
    boolean isLongText() default false;
    boolean isImage() default false;
    boolean isVideo() default false;
    //判断是不是addlist
    boolean isAdd() default false;
    boolean isUpdate() default false;

    boolean isDelete() default false;

}
