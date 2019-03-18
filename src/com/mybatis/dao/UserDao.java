package com.mybatis.dao;

import com.mybatis.po.User;


import java.util.List;

public interface UserDao {
    User findUserById(String id) throws Exception;

    List<User> findUserByName(String name) throws Exception;

    void addUser(User user) throws Exception;

    void delUser(String id) throws Exception;

    void updateUser(User user) throws Exception;
}
