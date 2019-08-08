package com.namimono.securitylogin.demo.config.utils;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wuxiao on 2019/7/29 14:35.
 */
public class ReflectUtils3 {

    /**
         * @param source :原对象
         * @param target :新修改的对象
         * @return
         */
        public static String packageModifyContent(Object source, Object target) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            StringBuffer content = new StringBuffer();
            if (null == source || null == target) {
                return "";
            }
            ArrayList<Field> allFieldList = new ArrayList<>();

//        获取当前类及其父类的所有属性存入allfieldList中
            Class<?> sourceClass = source.getClass();
            while (true) {
                Field[] fields = sourceClass.getDeclaredFields();
                //Arrays.asList()方法返回java.util.Arrays$ArrayList，而不是ArrayList
                List<Field> fieldsList = Arrays.asList(fields);
                ArrayList<Field> fieldsTemp = new ArrayList<>(fieldsList);

                allFieldList.addAll(fieldsTemp);
                sourceClass = sourceClass.getSuperclass();
                if (sourceClass == Object.class) {
                    break;
                }
            }


            //遍历所有属性
            for (Field field : allFieldList) {
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
                /**
                 * 以下为判断list情况
                 */
                Object sourceValue = getFieldValue(source, fieldName) == null ? "" : getFieldValue(source, fieldName);
                //获取对应的targetField值
                Object targetValue = getFieldValue(target, fieldName) == null ? "" : getFieldValue(target, fieldName);
                if (annotation.isAdd()) {
                    String[] splitA = StringUtils.delimitedListToStringArray(((String) targetValue), ",");
                    for (String s : splitA) {
                        content.append("&nbsp;&nbsp;&nbsp;&nbsp;增加了" + annotation.alias() + "：" + s + "<hr/>");
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
                            //调用getId方法
                            Method o1GetId = oClass.getMethod("getId");
                            Method o2GetId = aClass.getMethod("getId");

                            String o1Id = (String) o1GetId.invoke(o);
                            String o2Id = (String) o2GetId.invoke(o2);
                            //比较id是否相同，若相同将两个对象进行比较。
                            if (o1Id.equals(o2Id)) {
                                String s = packageModifyContent(o, o2);
                                content.append(s);
                                break;
                            }
                        }
                    }
                    continue;
                }
                if (annotation.isDelete()) {
                    String[] splitA = StringUtils.delimitedListToStringArray(((String) targetValue), ",");
                    for (String s : splitA) {
                        content.append("&nbsp;&nbsp;&nbsp;&nbsp;删除了" + annotation.alias() + "：" + s + "<hr/>");
                    }
                    continue;
                }


                /**
                 * 以下判断普通属性
                 */
                sourceValue = sourceValue.toString();
                targetValue = targetValue.toString();
                //若原属性与当前属性都为空，直接continue
                if (StringUtils.isEmpty(sourceValue) && StringUtils.isEmpty(targetValue)) {
                    continue;
                }
                if (!sourceValue.equals(targetValue)) {
                    //获取自定义注解
                    if (annotation.isSimpleCompare()) {
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
         * 由于只能传入子类obj，所以该方法要遍历子类+父类的所有方法
         */
        private static Object getFieldValue(Object obj, String fieldName) {
            Object fieldValue = null;
            if (null == obj) {
                return null;
            }
            ArrayList<Method> allMethodList = new ArrayList<>();
            Class<?> objClass = obj.getClass();
            while (true) {
                Method[] methods = objClass.getDeclaredMethods();
                List<Method> methodsList = Arrays.asList(methods);
                ArrayList<Method> methodsTemp =new ArrayList<>(methodsList);
                allMethodList.addAll(methodsTemp);
                objClass = objClass.getSuperclass();
                if (objClass == Object.class) {
                    break;
                }
            }
        for (Method method : allMethodList) {
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

}