package com.yanjun.anno.demo.annodemo.annoparser;

import com.yanjun.anno.demo.annodemo.anno.EasterEgg;
import com.yanjun.anno.demo.annodemo.anno.TestIt;
import com.yanjun.anno.demo.annodemo.anno.TesterInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.util.Objects.requireNonNull;

@Slf4j
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

    public void testItParse(Class obj) {
        try {
            Class<?> objClazz = requireNonNull(obj);
            if (objClazz.isAnnotationPresent(TesterInfo.class)) {
                TesterInfo testerInfo = objClazz.getAnnotation(TesterInfo.class);
                log.info("Tester info is {}, {}", testerInfo.priority(), testerInfo.author());
                if (testerInfo.tags() == null || testerInfo.tags().length <= 0) return;
                log.info("Tags are : ");
                for (String tag : testerInfo.tags()) {
                    log.info("tag: {}", tag);
                }
            }

            for (Method method : objClazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(TestIt.class)) {
                    TestIt testIt = method.getAnnotation(TestIt.class);
                    if (testIt.enabled()) {
                        log.info("Method {} is execute", method.getName());
                    }
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
