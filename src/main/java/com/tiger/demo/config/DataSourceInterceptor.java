package com.tiger.demo.config;

import com.tiger.demo.core.DataSourceType;
import com.tiger.demo.enums.DataSourceEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Date 2020/6/21
 * @Author tiger
 */
@Aspect
@Component
public class DataSourceInterceptor {

//    @Pointcut("@annotation(com.tiger.demo.core.DataSourceType)")
//    private void annotationPointCut(){
//        System.out.println("拦截到了注解************");
//    }
//
//    @Pointcut("execution(* com.tiger.demo.mapper.*.*(..))")
//    private void mapperPointCut(){
//        System.out.println("拦截到了mapper************");
//    }


    /**
     * 前置通知:目标方法执行前执行的通知
     * @param joinpoint
     */
    @Before("execution(* com.tiger.demo.mapper.*.*(..))")
    public void beforeMethod(JoinPoint joinpoint){
        Method method = ((MethodSignature) joinpoint.getSignature()).getMethod();
        DataSourceType annotation = method.getAnnotation(DataSourceType.class);
        DataSourceEnum value = annotation.value();
        DataSourceHolder.setDataSource(value.getValue());
    }

    @After("execution(* com.tiger.demo.mapper.*.*(..))")
    public void afterMethod(JoinPoint joinpoint){
//        Method method = ((MethodSignature) joinpoint.getSignature()).getMethod();
//        DataSourceType annotation = method.getAnnotation(DataSourceType.class);
//        DataSourceEnum value = annotation.value();
        DataSourceHolder.clear();
    }

//
//
//    @Around("mapperPointCut()")
//    public void beforeMethod2(JoinPoint joinpoint){
//        Method method = ((MethodSignature) joinpoint.getSignature()).getMethod();
//        DataSourceType annotation = method.getAnnotation(DataSourceType.class);
//        DataSourceEnum value = annotation.value();
//        DataSourceHolder.setDataSource(value.getValue());
//    }



}
