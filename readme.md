注意事项：
1、使用多数据时，只需要在mapper的方法上配置注解 @DataSourceType(DataSourceEnum.SLAVE_DB),
    这里如果不指定哪个数据源，或者不使用注解默认走的都是master数据源；
2、springboot使用mybatis的时候有一个bug,MybatisConfig类中有说明