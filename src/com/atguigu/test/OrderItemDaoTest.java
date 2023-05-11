package com.atguigu.test;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.iml.OrderItemDaoImpl;
import com.atguigu.pojo.OrderItem;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderItemDaoTest {
    OrderItemDao orderItemDao=new OrderItemDaoImpl();
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"关火的连接",6,new BigDecimal(100),new BigDecimal(600),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"java 从入门到精通", 1,new BigDecimal(100),new
                BigDecimal(100),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"javaScript 从入门到精通", 2,new
                BigDecimal(100),new BigDecimal(200),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"Netty 入门", 1,new BigDecimal(100),new
                BigDecimal(100),"1234567890"));
    }

    @Test
    public void showOrderDetail() {
        List<OrderItem> orders=orderItemDao.queryOrderItemsByOrderId("16797963551082");
        for(OrderItem order:orders){
            System.out.println(order);
        }
    }
}