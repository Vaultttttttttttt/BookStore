package com.atguigu.dao.iml;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
 * @author wxj27
 * @version 1.0
 * @description: TODO
 * @date 2023-03-26 9:20
 */

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql="insert into t_order_item(`id`,`name`,`count`,`price`,`total_price`,`order_id`) values (?,?,?,?,?,?)";
        return update(sql,orderItem.getId(),orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql="select `id` , `name`,`count`,`price`,`total_price` totalPrice,`order_id` orderId from t_order_item where `order_id`=?";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
