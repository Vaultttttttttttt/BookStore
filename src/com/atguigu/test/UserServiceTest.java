package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.iml.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService=new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null,"admin4","666","1234@ee.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"admin","admin","admin@atguigu.com")));
    }

    @Test
    public void existUsername() {
        if(userService.existUsername("admin")){
            System.out.println("admin整个用户名已存在，不可再注册");
        }else{
            System.out.println("admin整个用户名不存在，可以注册");
        }
        if(userService.existUsername("admin5")){
            System.out.println("admin5整个用户名已存在，不可再注册");
        }else{
            System.out.println("admin5整个用户名不存在，可以注册");
        }
    }
}