package com.atguigu.service.iml;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.iml.BookDaoImpl;
import com.atguigu.dao.iml.OrderDaoImpl;
import com.atguigu.dao.iml.OrderItemDaoImpl;
import com.atguigu.pojo.*;
import com.atguigu.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

/**
* @description: TODO
* @author wxj27
* @date 2023-03-26 9:53
* @version 1.0
*/

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao=new OrderDaoImpl();
    OrderItemDao orderItemDao=new OrderItemDaoImpl();
    BookDao bookDao=new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号===唯一性
        String orderId=System.currentTimeMillis()+""+userId;

        Order order=new Order(orderId,new Timestamp(System.currentTimeMillis()),cart.getTotalPrice(),0,userId);

        orderDao.saveOrder(order);

        for(Map.Entry<Integer, CartItem> entry:cart.getItems().entrySet()){
            CartItem cartItem=entry.getValue();
            OrderItem orderItem=new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            orderItemDao.saveOrderItem(orderItem);

            Book book = bookDao.queryBookById(cartItem.getId());

            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());

            if(book.getStock()==0){
                bookDao.deleteBookById(book.getId());
            }else{
                bookDao.updateBook(book);
            }
        }

        cart.clean();

        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        List<Order> list=orderDao.queryOrders();
        return list;
    }

    @Override
    public List<Order> showMyOrders(Integer id) {
        List<Order> list=orderDao.queryOrdersByUserId(id);
        return list;
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        List<OrderItem> list=orderItemDao.queryOrderItemsByOrderId(orderId);
        return list;
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,1);
    }

    @Override
    public void receiveOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,2);
    }
}
