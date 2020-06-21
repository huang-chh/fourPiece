package com.tiger.demo.config;

import com.tiger.demo.core.DataSourceType;
import com.tiger.demo.enums.DataSourceEnum;
import org.apache.commons.lang3.StringUtils;
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
        //不使用注解的使用，默认走master数据源,否则使用注解中的数据源
        if (annotation==null){
            DataSourceHolder.setDataSource(DataSourceEnum.MASTE_DB.getValue());
        }else{
            DataSourceEnum value = annotation.value();
            DataSourceHolder.setDataSource(value.getValue());
        }
    }

    @After("execution(* com.tiger.demo.mapper.*.*(..))")
    public void afterMethod(JoinPoint joinpoint){
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
