package com.atguigu.dao;

import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
* @description: TODO
* @author wxj27
* @date 2023-03-26 9:14
* @version 1.0
*/

public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);

    public List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
