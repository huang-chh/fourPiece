package com.tiger.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2020/6/21
 * @Author tiger
 */
@Configuration
@MapperScan(basePackages = "com.tiger.demo.mapper",sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisConfig {
    @Value("${mybatis.type-aliases-package}")
    private String type_aliases_package;

    @Value("${mybatis.mapper-locations}")
    private String mapper_locations;

    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier("master") DataSource master,
                                               @Qualifier("slave") DataSource slave,
                                                @Qualifier("impala") DataSource impala){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(master);
        Map<Object,Object> dataSources = new HashMap<>();
        dataSources.put("master",master);
        dataSources.put("slave",slave);
        dataSources.put("impala",impala);
        dynamicDataSource.setTargetDataSources(dataSources);
        return dynamicDataSource;
    }

    @Bean
    public MultiDataSourceTransactionFactory multiDataSourceTransactionFactory(){
        return new MultiDataSourceTransactionFactory();
    }


    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource")DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //如果打包后启动报错扫描不到类别名的时候，就把下面这句放开
        //sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        sqlSessionFactoryBean.setTransactionFactory(multiDataSourceTransactionFactory());
        sqlSessionFactoryBean.setTypeAliasesPackage(type_aliases_package);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mapper_locations));
        return sqlSessionFactoryBean.getObject();
    }



}
