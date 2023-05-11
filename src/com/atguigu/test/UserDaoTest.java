package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.iml.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void queryUserByUsername() {
        UserDao userDao=new UserDaoImpl();
        System.out.println(userDao.queryUserByUsername("admin"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        UserDao userDao=new UserDaoImpl();
        System.out.println(userDao.queryUserByUsernameAndPassword("admin","admin"));
        System.out.println(userDao.queryUserByUsernameAndPassword("admin","admin1234"));
    }

    @Test
    public void saveUser() {
        UserDao userDao=new UserDaoImpl();
        userDao.saveUser(new User(null,"admin2","123456","12@qq.com"));
    }
}