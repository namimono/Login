package com.namimono.securitylogin.demo.config.utils;

import org.springframework.util.StringUtils;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wuxiao on 2019/7/29 14:35.
 */
public class ReflectUtils2 {

    /**
     * @param source :原对象
     * @param target :新修改的对象
     * @return
     */
    public static <T> String packageModifyContent(T source, T target) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        StringBuffer content = new StringBuffer();
        if (null == source || null == target) {
            return "";
        }

        /**
         * 首先解决List，即子类中的变化。
         */
        content = ResolveList(source, target);
        //取出父类
        Class<?> sourceClass = source.getClass();
        while (true) {
            if (sourceClass.getSuperclass() == Object.class) {
                break;
            }
            sourceClass = sourceClass.getSuperclass();
        }

        //取出父类的属性
        Field[] sourceFields = sourceClass.getDeclaredFields();
        for (Field field : sourceFields) {
            //获取属性注解
            MyAnnotation annotation = field.getAnnotation(MyAnnotation.class);
            if (annotation == null) {
                continue;
            }
            if (annotation.isIgnore()) {
                continue;
            }
            //获取属性名
            String fieldName = field.getName();

            //获取field值
            //由于会遍历所有属性的get方法，有的属性为null，所以会返回null值
            Object sourceValue = getFieldValue(source, fieldName) == null ? "" : getFieldValue(source, fieldName).toString();
            //获取对应的targetField值
            Object targetValue = getFieldValue(target, fieldName) == null ? "" : getFieldValue(target, fieldName).toString();
//            /**
//             * 以下为判断list情况
//             */
//            if (annotation.isAdd()) {
//                String[] splitA = StringUtils.delimitedListToStringArray(((String) targetValue), ",");
//                for (String s : splitA) {
//                    content.append("&nbsp;&nbsp;&nbsp;&nbsp;增加了" + annotation.name() + "：" + s + "<hr/>");
//                }
//                continue;
//            }
//
//            if (annotation.isUpdate()) {
//                List sourceValue1 = (List) sourceValue;
//                List targetValue1 = (List) targetValue;
//                for (Object o : sourceValue1) {
//                    for (Object o2 : targetValue1) {
//                        //通过反射，调用子类方法
//                        Class<?> oClass = o.getClass();
//                        Class<?> aClass = o2.getClass();
//
//                        Method o1GetId = oClass.getMethod("getId");
//                        Method o2GetId = aClass.getMethod("getId");
//
//                        String o1Id = (String) o1GetId.invoke(o);
//                        String o2Id = (String) o2GetId.invoke(o);
//
//                        if (o1Id.equals(o2Id)) {
//                            String s = packageModifyContent(o, o2);
//                            content.append(s);
//                        }
//                    }
//                }
//                continue;
//            }
//            if (annotation.isDelete()) {
////                String deleteInfo = targetValue;
//                String[] splitA = StringUtils.delimitedListToStringArray(((String) targetValue), ",");
//                for (String s : splitA) {
//                    content.append("&nbsp;&nbsp;&nbsp;&nbsp;删除了" + annotation.name() + "：" + s + "<hr/>");
//                }
//                continue;
//            }


            /**
             * 以下判断普通属性
             */
            //若原属性与当前属性都为空，直接continue
            if (StringUtils.isEmpty(sourceValue) && StringUtils.isEmpty(targetValue)) {
                continue;
            }
            if (!sourceValue.equals(targetValue)) {
                //获取自定义注解
                if (annotation.isImage()) {
                    content.append(annotation.alias() + "被修改<hr/>");
                    continue;
                }
                if (annotation.isLongText()) {
                    content.append(annotation.alias() + "被修改<hr/>");
                    continue;
                }
                if (annotation.isVideo()) {
                    content.append(annotation.alias() + "被修改<hr/>");
                    continue;
                }
                content.append(annotation.alias() + "：<br/>");
                content.append("&nbsp;&nbsp;&nbsp;&nbsp;原：" + sourceValue + "<br/>");
                content.append("&nbsp;&nbsp;&nbsp;&nbsp;改：" + targetValue + "<hr/>");
            }

        }
        return content.toString();
    }

    /**
     * @param obj       对象
     * @param fieldName 需要获取值的属性名
     * @return
     */

    /**
     *  由于只能传入子类obj，所以该方法要遍历子类+父类的所有方法
     */
    private static Object getFieldValue(Object obj, String fieldName) {
        Object fieldValue = null;
        if (null == obj) {
            return null;
        }
        Method[] methods = obj.getClass().getDeclaredMethods();
        Method[] methods2 = obj.getClass().getSuperclass().getDeclaredMethods();
        //Arrays.asList()方法返回java.util.Arrays$ArrayList，而不是ArrayList
        List<Method> List1 = Arrays.asList(methods);
        List<Method> List2 = Arrays.asList(methods2);
        ArrayList<Method> methodsList1 = new ArrayList<>(List1);
        ArrayList<Method> methodsList2 = new ArrayList<>(List2);

        methodsList1.addAll(methodsList2);
        for (Method method : methodsList1) {
            String methodName = method.getName();
            if (!methodName.startsWith("get")) {
                continue;
            }

//          截取get方法后面的内容，转为大写并与传入属性名(同转为大写)比较，如果相等，调用该方法并返回结果
            if (methodName.startsWith("get") && methodName.substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
                try {
                    fieldValue = method.invoke(obj);
                } catch (Exception e) {
                    System.out.println("取值出错，方法名 " + methodName);
                }
            }
        }
        return fieldValue;
    }

    /**
     *
     * @param source
     * @param target
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private static <T> StringBuffer ResolveList(T source, T target) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        StringBuffer content = new StringBuffer("");
        //取出source类
        Class<?> sourceClass = source.getClass();


        //取出source的属性
        Field[] sourceFields = sourceClass.getDeclaredFields();
        for (Field field : sourceFields) {
            //获取属性注解
            MyAnnotation annotation = field.getAnnotation(MyAnnotation.class);
            if (annotation == null) {
                continue;
            }
            //获取属性名
            String fieldName = field.getName();

//            Type type = field.getGenericType();
//            String typeName = type.getTypeName();
//            if (type.getTypeName().equals())
            //获取field值
            //由于会遍历所有属性的get方法，有的属性为null，所以会返回null值
            Object sourceValue = getFieldValue(source, fieldName) == null ? "" : getFieldValue(source, fieldName);
            //获取对应的targetField值
            Object targetValue = getFieldValue(target, fieldName) == null ? "" : getFieldValue(target, fieldName);
            if (annotation.isIgnore()) {
                continue;
            }
            /**
             * 以下为判断list情况
             */
            if (annotation.isAdd()) {
                String[] splitA = StringUtils.delimitedListToStringArray(((String) targetValue), ",");
                for (String s : splitA) {
                    content.append("&nbsp;&nbsp;&nbsp;&nbsp;增加了" + annotation.name() + "：" + s + "<hr/>");
                }
                continue;
            }

            if (annotation.isUpdate()) {
                List sourceValue1 = (List) sourceValue;
                List targetValue1 = (List) targetValue;
                for (Object o : sourceValue1) {
                    for (Object o2 : targetValue1) {
                        //通过反射，调用子类方法
                        Class<?> oClass = o.getClass();
                        Class<?> aClass = o2.getClass();
                        Method[] methods = oClass.getMethods();

                        Method o1GetId = oClass.getMethod("getId");
                        Method o2GetId = aClass.getMethod("getId");

                        String o1Id = (String) o1GetId.invoke(o);
                        String o2Id = (String) o2GetId.invoke(o);

                        if (o1Id.equals(o2Id)) {
                            String s = packageModifyContent(o, o2);
                            content.append(s);
                        }
                    }
                }
                continue;
            }
            if (annotation.isDelete()) {
//                String deleteInfo = targetValue;
                String[] splitA = StringUtils.delimitedListToStringArray(((String) targetValue), ",");
                for (String s : splitA) {
                    content.append("&nbsp;&nbsp;&nbsp;&nbsp;删除了" + annotation.name() + "：" + s + "<hr/>");
                }
                continue;
            }
        }

        return content;
    }
}