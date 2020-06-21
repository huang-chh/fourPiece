package com.tiger.demo.controller;

import com.tiger.demo.entity.MyOrder;
import com.tiger.demo.service.impl.MyOrderService;
import org.apache.commons.lang3.StringUtils;
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
        if (StringUtils.isNotBlank(id)){
            return myOrderService.getInfo("2");
        }else{
            return myOrderService.getOrderList();
        }
    }

    @RequestMapping("/insert")
    public String insert(String name){
        MyOrder order = new MyOrder();
//        order.setId(3);
        order.setName(name);
        int a = myOrderService.insert(order);
        if (a>0){
            return "success";
        }else{
            return "fail";
        }
    }

}
