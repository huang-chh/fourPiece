package com.tiger.demo.service;

import com.tiger.demo.config.DataSourceHolder;
import com.tiger.demo.core.DataSourceType;
import com.tiger.demo.entity.MyOrder;
import com.tiger.demo.enums.DataSourceEnum;
import com.tiger.demo.mapper.MyFirstTableMapper;
import com.tiger.demo.mapper.MyOrderMapper;
import com.tiger.demo.mapper.ZhuaMapper;
import com.tiger.demo.service.impl.MyOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Date 2020/6/21
 * @Author tiger
 */

@Service
public class MyOrderServiceImpl implements MyOrderService {

    @Resource
    private MyOrderMapper myOrderMapper;
    @Resource
    private ZhuaMapper zhuaMapper;
    @Resource
    private MyFirstTableMapper myFirstTableMapper;

    @Override
    public List<MyOrder> getOrderList(String id) {
        if("666".equals(id)){
//            DataSourceHolder.setDataSource("slave");
            System.out.println(zhuaMapper.getZhuaById(1).toString());
            return null;
        }else if("777".equals(id)){
            System.out.println(myFirstTableMapper.getInfoById(1).toString());
            return null;
        }else {
            return myOrderMapper.getOrderList();
        }

    }

}
