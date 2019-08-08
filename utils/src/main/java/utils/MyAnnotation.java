package utils;

import java.lang.annotation.*;

/**
 * Created by wuxiao on 2019/7/29 14:40.
 */
@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME )
public @interface MyAnnotation {
    /**
     * 该字段中文名
     */
    String alias() default "";


    /**
     * 是否忽略该字段，一般 list字段(未分开前的list)上为true
     */
    boolean isIgnore() default false;

    /**
     * 字段为长文本时为true
     */
    boolean isSimpleCompare() default false;

    /**
     * 在addInfo上为true
     */
    boolean isAdd() default false;

    /**
     * 在updateList上为true
     */
    boolean isUpdate() default false;

    /**
     * 在deleteInfo上为true
     */
    boolean isDelete() default false;

}
