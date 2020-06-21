package com.tiger.demo.core;

import com.tiger.demo.enums.DataSourceEnum;

import java.lang.annotation.*;

/**
 * @Date 2020/6/21
 * @Author tiger
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceType {
    DataSourceEnum value() default DataSourceEnum.MASTE_DB;
}
