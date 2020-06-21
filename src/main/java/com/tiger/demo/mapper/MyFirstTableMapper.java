package com.tiger.demo.mapper;

import com.tiger.demo.core.DataSourceType;
import com.tiger.demo.entity.MyFirstTable;
import com.tiger.demo.enums.DataSourceEnum;
import org.apache.ibatis.annotations.Param;

/**
 * @Date 2020/6/21
 * @Author tiger
 */

public interface MyFirstTableMapper {
    @DataSourceType(DataSourceEnum.IMPALA_DB)
    MyFirstTable getInfoById(@Param("id") int id);

}
