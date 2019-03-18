package test.mybatis.userdao;

import com.mybatis.dao.UserDao;
import com.mybatis.dao.UserDaoImpl;
import com.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class TestImpl {
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void before()
    {
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    @Test
    public void findUserById() throws Exception {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = userDao.findUserById("1");
        System.out.print(user);

    }
    @Test
    public void findUserByName() throws Exception {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        List<User> users = userDao.findUserByName("小明");
        System.out.print(users);

    }
    @Test
    public void addUser() throws Exception {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = new User();
        user.setUsername("asd");
        user.setSex("2");
        user.setAddress("石家庄");
        user.setBirthday(new Date());
        userDao.addUser(user);
        System.out.print(user.getId());
    }
}
