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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Date 2020/6/21
 * @Author tiger
 */

@Transactional
@Service
public class MyOrderServiceImpl implements MyOrderService {

    @Resource
    private MyOrderMapper myOrderMapper;
    @Resource
    private ZhuaMapper zhuaMapper;
    @Resource
    private MyFirstTableMapper myFirstTableMapper;


    @Override
    public List<MyOrder> getOrderList() {
        return myOrderMapper.getOrderList();
    }

    @Override
    public Object getInfo(String id) {
        return zhuaMapper.getZhuaById(new Integer(id));
    }

    @Override
    public int insert(MyOrder myOrder) {
        int a = myOrderMapper.insert(myOrder);
//        double b = 1/0;
        return a;
    }


}
