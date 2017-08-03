package com.mgz.nongzitong.nongzitong.utils;

import java.lang.reflect.Field;

/**
 * Created by john on 2017/7/5.
 */

public class ReflectUtil {
    public ReflectUtil() {
    }

    public static String getterNameFromField(Field field) {
        return "get" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);
    }

    public static String setterNameFromField(Field field) {
        return "set" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);
    }
}
