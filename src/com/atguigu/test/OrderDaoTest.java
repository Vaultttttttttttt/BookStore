package com.atguigu.test;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.iml.OrderDaoImpl;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {
    OrderDao orderDao=new OrderDaoImpl();
    @Test
    public void saveOrder(){
        orderDao.saveOrder(new Order("12345678922",new Timestamp(System.currentTimeMillis()),new BigDecimal(100),0,1));
    }

    @Test
    public void queryOrders(){
        List<Order> orders=orderDao.queryOrders();
        for(Order order:orders){
            System.out.println(order);
        }
    }

    @Test
    public void queryOrdersByUserId() {
        List<Order> orders=orderDao.queryOrdersByUserId(1);
        for(Order order:orders){
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus() {
        orderDao.changeOrderStatus("1234567890",1);
    }
}