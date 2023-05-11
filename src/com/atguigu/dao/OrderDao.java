package com.atguigu.dao;

import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
* @description: TODO
* @author wxj27
* @date 2023-03-26 9:14
* @version 1.0
*/

public interface OrderDao {
    public int saveOrder(Order order);

    public List<Order> queryOrders();

    public List<Order> queryOrdersByUserId(Integer id);

    public void changeOrderStatus(String orderId,Integer status);
}
