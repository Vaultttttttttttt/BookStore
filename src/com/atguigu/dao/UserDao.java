package com.atguigu.dao;

import com.atguigu.pojo.User;

public interface UserDao {



    /*
    * 根据用户名查用户信息
    * */
    public User queryUserByUsername(String username);

    /*
     * 根据用户名和密码查用户信息
     * */
    public User queryUserByUsernameAndPassword(String username,String password);

    /*
    * 保存用户信息
    * */
    public int saveUser(User user);


}
