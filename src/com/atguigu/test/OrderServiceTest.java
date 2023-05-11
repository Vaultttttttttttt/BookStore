package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.service.OrderService;
import com.atguigu.service.iml.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceTest {
    OrderService orderService=new OrderServiceImpl();
    @Test
    public void createOrder() {
        Cart cart=new Cart();

        cart.addItem(new CartItem(1,"wait",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"wait",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"我是乱杀哥",1,new BigDecimal(100),new BigDecimal(100)));

        System.out.println(orderService.createOrder(cart,2));
    }

    @Test
    public void showAllOrders() {
        List<Order> list=orderService.showAllOrders();
        for(Order order:list){
            System.out.println(order);
        }
    }

    @Test
    public void showMyOrders() {
        List<Order> list=orderService.showMyOrders(2);
        for(Order order:list){
            System.out.println(order);
        }
    }

    @Test
    public void showOrderDetail() {
        List<OrderItem> list=orderService.showOrderDetail("16797963551082");
        for(OrderItem orderItem:list){
            System.out.println(orderItem);
        }
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder("16797963551082");
    }

    @Test
    public void receiveOrder() {
        orderService.receiveOrder("16797963551082");
    }
}