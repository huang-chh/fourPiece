package com.tiger.demo.config;

import com.tiger.demo.enums.DataSourceEnum;

/**
 * @Date 2020/6/21
 * @Author tiger
 */

public class DataSourceHolder {
    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSource(DataSourceEnum db) {
        setDataSource(db.value());
    }

    /**
     * 设置数据源
     *
     * @param db
     */
    public static void setDataSource(String db) {
        contextHolder.set(db);
    }

    /**
     * 取得当前数据源
     *
     * @return
     */
    public static String getDataSource() {
        return contextHolder.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clear() {
        contextHolder.remove();
    }
}
