package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {


    @Test
    public void addItem() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"wait",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"wait",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"我是乱杀哥",1,new BigDecimal(100),new BigDecimal(100)));

        System.out.println(cart);
    }

    @Test
    public void delete() {
        Cart cart=new Cart();

        cart.addItem(new CartItem(1,"wait",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"wait",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"我是乱杀哥",1,new BigDecimal(100),new BigDecimal(100)));

        cart.delete(1);

        System.out.println(cart);
    }

    @Test
    public void clean() {
        Cart cart=new Cart();

        cart.addItem(new CartItem(1,"wait",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"wait",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"我是乱杀哥",1,new BigDecimal(100),new BigDecimal(100)));

        cart.clean();

        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart=new Cart();

        cart.addItem(new CartItem(1,"wait",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"wait",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"我是乱杀哥",1,new BigDecimal(100),new BigDecimal(100)));

        cart.updateCount(2,10);

        System.out.println(cart);
    }
}