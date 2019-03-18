package com.mybatis.dao;

import com.mybatis.po.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao{
    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User findUserById(String id) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("com.mybatis.dao.UserDao.findUserById" , "34");
        sqlSession.close();
        return user;
    }

    @Override
    public List<User> findUserByName(String name) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("com.mybatis.dao.UserDao.findUserByName", "小明");
        sqlSession.close();
        return users;
    }

    @Override
    public void addUser(User user) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("com.mybatis.dao.UserDao.addUser" , user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void delUser(String id) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("com.mybatis.dao.UserDao.delUser" , "1");
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateUser(User user) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.update("com.mybatis.dao.UserDao.updateUser" , user);
        sqlSession.commit();
        sqlSession.commit();
        sqlSession.close();
    }
}
