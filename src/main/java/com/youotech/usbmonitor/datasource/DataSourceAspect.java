package com.youotech.usbmonitor.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author sundongyang
 * @date 2017/11/23 0023
 * @time 15:46
 * @desc 定义一个数据源切面类，通过aop访问，获取方法上的自定义注解，然后根据注解内容尽情判断，动态设置数据源
 * @see
 */
public class DataSourceAspect {

    public void pointCut() {
    }

    public void before(JoinPoint point) {
        Object target = point.getTarget();
        System.out.println(target.toString());
        String method = point.getSignature().getName();
        System.out.println(method);
        Class<?>[] classz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
                .getMethod().getParameterTypes();
        try {
            Method m = classz[0].getMethod(method, parameterTypes);
            System.out.println(m.getName());
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource data = m.getAnnotation(DataSource.class);
                HandleDataSource.putDataSource(data.value());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
