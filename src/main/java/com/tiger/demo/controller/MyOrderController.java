package com.tiger.demo.controller;

import com.tiger.demo.service.impl.MyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2020/6/21
 * @Author tiger
 */

@RestController
@RequestMapping("/order")
public class MyOrderController {
    @Autowired
    private MyOrderService myOrderService;

    @RequestMapping("/show")
    public Object show(String id){
        return myOrderService.getOrderList(id);
    }

}
