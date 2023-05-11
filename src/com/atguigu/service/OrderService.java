package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
* @description: TODO
* @author wxj27
* @date 2023-03-26 9:51
* @version 1.0
*/

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);

    public List<Order> showAllOrders();

    public List<Order> showMyOrders(Integer id);

    public List<OrderItem> showOrderDetail(String orderId);

    public void sendOrder(String orderId);

    public void receiveOrder(String orderId);
}
