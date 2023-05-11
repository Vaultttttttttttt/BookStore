package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    static {
        try {
            Properties properties=new Properties();
            //读取jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取数据库连接池的连接
    //如果返回null说明获取连接失败
    public static Connection getConnection() {
            Connection connection = connectionThreadLocal.get();

            if(connection == null){
                try {
                    connection = dataSource.getConnection();
                    connection.setAutoCommit(false);
                    connectionThreadLocal.set(connection);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return connection;
    }

    /**
    * @description: 提交事务，并关闭释放连接
    * @param:
    * @return: void
    * @author wxj27
    * @date: 2023-03-29 19:24
    */
    public static void commitAndClose(){
        Connection connection = connectionThreadLocal.get();
        if(connection!=null){
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        connectionThreadLocal.remove();
    }

    /**
    * @description: 回滚事务
    * @param:
    * @return: void
    * @author wxj27
    * @date: 2023-03-29 19:25
    */
    public static void rollbackAndClose(){
        Connection connection = connectionThreadLocal.get();
        if(connection!=null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        connectionThreadLocal.remove();
    }

//    //关闭连接，放回数据库连接池
//    public static void close(Connection connection) {
//        if(connection!=null){
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
