package com.yanjun.anno.demo.annodemo.annoparser;

import com.yanjun.anno.demo.annodemo.anno.EasterEgg;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.util.Objects.requireNonNull;

public class AnnoParser {

    public void parse(Object obj) {
        try {
            Class<?> objectClass = requireNonNull(obj).getClass();
            for (Method method : objectClass.getMethods()) {
                if (method.isAnnotationPresent(EasterEgg.class)) {
                    doSomeEasterEggThings(method, obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doSomeEasterEggThings(Method method, Object obj) throws InvocationTargetException, IllegalAccessException {
        String value = method.getAnnotation(EasterEgg.class).value();
        if (StringUtils.isEmpty(value)) return;
        System.out.println(value);
        method.invoke(obj);
    }
}
