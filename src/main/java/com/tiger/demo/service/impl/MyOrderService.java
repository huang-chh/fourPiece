package com.tiger.demo.service.impl;

import com.tiger.demo.entity.MyOrder;

import java.util.List;

/**
 * @Date 2020/6/21
 * @Author tiger
 */

public interface MyOrderService {
    List<MyOrder> getOrderList(String id);
}
