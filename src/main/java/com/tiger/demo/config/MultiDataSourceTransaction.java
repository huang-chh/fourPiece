package com.tiger.demo.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Date 2020/6/21
 * @Author tiger
 */

public class MultiDataSourceTransaction implements Transaction{

        public static final Logger logger = LoggerFactory.getLogger(MultiDataSourceTransaction.class);

        private static final String DEFAULT_DB_NAME = "default_db_name_index_";

        private final DataSource dataSource;

        private ConcurrentMap<String, Connection> connectionMap;

        private AtomicInteger num = new AtomicInteger(1);

        public MultiDataSourceTransaction(DataSource dataSource) {
            Assert.notNull(dataSource, "No DataSource specified");
            this.dataSource = dataSource;
            connectionMap = new ConcurrentHashMap<>();
        }

        /**
         * service方法中第一次调用Mapper的时候开启事务，调用这个方法。
         * 之后再一个事务中，再调用其他的mapper就不执行了，会调用getConnection获取连接。
         * Connection conn = dataSource.getConnection();这个dataSource是动态数据源。在调用getConnection的时候
         * 会先调AbstractRoutingDataSource的determineCurrentLookupKey(){return DataSourceContextHolder.getDataSource();}
         * 获取设置的数据源，如果Mapper上没加@DataSource或者加了但是没有设置属性值，那么就走默认数据源。默认数据源是在application.yml中设置的。
         * @param dataSource
         * @param level
         */
        public MultiDataSourceTransaction(DataSource dataSource, TransactionIsolationLevel level) {
            Assert.notNull(dataSource, "No DataSource specified");
            this.dataSource = dataSource;
            connectionMap = new ConcurrentHashMap<>();
        }

        /**
         * 在一个事务中独享一个connectionMap
         * {@inheritDoc}
         */
        @Override
        public Connection getConnection() throws SQLException {
            String dbName = DataSourceHolder.getDataSource();
            //否是从缓存中获取默认数据源连接
            if(StringUtils.isBlank(dbName) && connectionMap.size() > 0){
                //dbName为，使用默认的数据源获取连接，先判断connectionMap中有没有缓存的默认数据源，如果有就获取一个
                for(String key : connectionMap.keySet()){
                    if(key.indexOf(DEFAULT_DB_NAME) > -1){
                        //如果dbname为空，那么就是走默认数据源连接，如果从缓存中能获取到默认数据源连接，就返回
                        return connectionMap.get(key);
                    }
                }
            }
            //如果dbName为空，创建新的默认数据源连接
            //如果dbName不为空，那么就是制定了数据源，如果缓存中不包含，那么就创建新的数据源连接
            if (StringUtils.isBlank(dbName) || !connectionMap.containsKey(dbName)) {
                //如果不能从缓存中获取默认数据源或者数据源名称不为空，但是该数据源名称没有对应的缓存连接，创建新的连接
                try {
                    //调用该方法的时候会先调用AbstractRoutingDataSource子类的一个重写的方法叫determineCurrentLookupKey()方法，目的是为了确定使用哪个数据源。
                    Connection conn = dataSource.getConnection();
                    conn.setAutoCommit(false);
                    String key = dbName;
                    if (StringUtils.isBlank(dbName)) {
                        //走默认数据源 dbName为空
                        int andIncrement = num.getAndIncrement();
                        key = DEFAULT_DB_NAME + andIncrement;
                    }
                    connectionMap.put(key, conn);
                    return conn;
                } catch (SQLException ex) {
                    throw new CannotGetJdbcConnectionException("Could not get JDBC Connection", ex);
                }
            }
            return connectionMap.get(dbName);

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void commit() throws SQLException {
            for (Connection connection : connectionMap.values()) {
                if (connection != null && !connection.getAutoCommit()) {
                    connection.commit();
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void rollback() throws SQLException {
            for (Connection connection : connectionMap.values()) {
                if (connection != null && !connection.getAutoCommit()) {
                    connection.rollback();
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void close() throws SQLException {
            for (Connection connection : connectionMap.values()) {
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, this.dataSource);
                }
            }
        }

        public Integer getTimeout() throws SQLException {
            return null;
        }

        public static void main(String[] args) {
            String s = "abcd";
            System.out.println(s.indexOf("1"));
        }

}
