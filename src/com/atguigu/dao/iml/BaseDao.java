package com.atguigu.dao.iml;

import com.atguigu.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    //使用DbUtils操作数据库
    private QueryRunner queryRunner=new QueryRunner();

    /*
    * update（）方法用来执行：insert/update/delete语句
    * 如果返回-1，则执行失败，反之成功
    * */
    public int update(String sql,Object ...args){
        Connection connection= JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /*
    * 查询返回一个javaBean的sql语句
    * type  返回的对象类型
    * sql   执行的sql语句
    * args  sql对应的参数值
    * <T>   返回的类型的泛型
    *  */
    public <T> T queryForOne(Class<T> type,String sql,Object...args) {
        Connection connection=JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /*
     * 查询返回多个javaBean的sql语句
     * type  返回的对象类型
     * sql   执行的sql语句
     * args  sql对应的参数值
     * <T>   返回的类型的泛型
     *  */
    public <T> List<T> queryForList(Class<T> type,String sql,Object ...args){
        Connection connection=JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public <T> List<T> queryForListNoArgs(Class<T> type,String sql){
        Connection connection=JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<T>(type));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    //执行返回一行一列的 sql 语句
    public Object queryForSingleValue(String sql,Object ...args){
        Connection connection=JdbcUtils.getConnection();

        try {
            return queryRunner.query(connection,sql,new ScalarHandler(),args);
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Object queryForSingleValueNoArgs(String sql){
        Connection connection=JdbcUtils.getConnection();

        try {
            return queryRunner.query(connection,sql,new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
