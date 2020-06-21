package com.tiger.demo.mapper;

import com.tiger.demo.core.DataSourceType;
import com.tiger.demo.entity.MyOrder;

import java.util.List;

/**
 * @Date 2020/6/21
 * @Author tiger
 */

public interface MyOrderMapper {
    @DataSourceType
    List<MyOrder> getOrderList();
}
