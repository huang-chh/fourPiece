package com.tiger.demo.mapper;

import com.tiger.demo.core.DataSourceType;
import com.tiger.demo.entity.Zhua;
import com.tiger.demo.enums.DataSourceEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Date 2020/6/21
 * @Author tiger
 */

public interface ZhuaMapper {
    @DataSourceType(DataSourceEnum.SLAVE_DB)
    Zhua getZhuaById(@Param("id") int id);
    @DataSourceType(DataSourceEnum.SLAVE_DB)
    int insert(Zhua zhua);
}
