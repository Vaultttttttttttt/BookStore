package com.atguigu.dao.iml;

import com.atguigu.dao.OrderDao;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
* @description: TODO
* @author wxj27
* @date 2023-03-26 9:16
* @version 1.0
*/

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql="insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values (?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreatTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        String sql="select `order_id` orderId,`create_time` creatTime,`price` price,`status` status,`user_id` userId from t_order";
        return queryForListNoArgs(Order.class,sql);
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer id) {
        String sql="select `order_id` orderId,`create_time` creatTime,`price` price,`status` status,`user_id` userId from t_order where`user_id` = ?";
        return queryForList(Order.class,sql,id);
    }

    @Override
    public void changeOrderStatus(String orderId, Integer status) {
        String sql="update t_order set  status = ? where order_id=?";
        update(sql,status,orderId);
    }
}
