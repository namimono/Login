package com.namimono.securitylogin.demo.config.utils;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by wuxiao on 2019/7/29 14:35.
 */
public class ReflectUtils {

    /**
     *
     * @param source :原对象
     * @param target :新修改的对象
     * @return
     */
    public static String packageModifyContent(Object source, Object target) {
        StringBuffer content = new StringBuffer();
        if(null == source || null == target) {
            return "";
        }
        //取出source类
        Class<?> sourceClass = source.getClass();
        //取出source的属性
        Field[] sourceFields = sourceClass.getDeclaredFields();
        for(Field field : sourceFields) {
            //获取属性名
            String fieldName = field.getName();
            //获取field值
            //由于会遍历所有属性的get方法，有的属性为null，所以会返回null值
            String sourceValue = getFieldValue(source, fieldName) == null ? "" : getFieldValue(source, fieldName).toString();
            //获取对应的targetField值
            String targetValue = getFieldValue(target, fieldName) == null ? "" : getFieldValue(target, fieldName).toString();
            //若原属性与当前属性都为空，直接continue
            if(StringUtils.isEmpty(sourceValue) && StringUtils.isEmpty(targetValue)) {
                continue;
            }

            if(!sourceValue.equals(targetValue)) {
                //获取自定义注解
//            content.append("酒店展示名称：<br/>");
                MyAnnotation annotation = field.getAnnotation(MyAnnotation.class);
                if (annotation.isIgnore()==true){
                    continue;
                }
                content.append(annotation.alias() + "：<br/>");
                content.append("&nbsp;&nbsp;&nbsp;&nbsp;原：" + sourceValue+ "<br/>");
                content.append("&nbsp;&nbsp;&nbsp;&nbsp;改：" + targetValue+ "<hr/>");
            }

        }
        return content.toString();
    }

    /**
     *
     * @param obj 对象
     * @param fieldName 需要获取值的属性名
     * @return
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        Object fieldValue = null;
        if(null == obj) {
            return null;
        }
        Method[] methods = obj.getClass().getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if(!methodName.startsWith("get")) {
                continue;
            }

//          截取get方法后面的内容，转为大写并与传入属性名(同转为大写)比较，如果相等，调用该方法并返回结果
            if(methodName.startsWith("get") && methodName.substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
                try {
                    fieldValue = method.invoke(obj, new Object[] {});
                } catch (Exception e) {
                    System.out.println("取值出错，方法名 " + methodName);
                    continue;
                }
            }
        }
        return fieldValue;
    }
}