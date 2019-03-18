package test.mybatis.sqlmapper;

import com.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class TestMapper {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() {

        //mybatis配置文件
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            //创建会话工厂，传入mybatis配置文件信息
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findUserById() {
        //通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession操作数据库
        // 第一个参数：映射文件中statement的id，等于=namespace+"."+statement的id
        // 第二个参数：指定和映射文件中所匹配的parameterType类型的参数
        // sqlSession.selectOne结果 是与映射文件中所匹配的resultType类型的对象
        // selectOne查询出一条记录
        User user = sqlSession.selectOne("com.mybatis.dao.UserDao.findUserById", "1");
        System.out.print(user);
        //释放资源
        sqlSession.close();
    }

    @Test
    public void findUserByName() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("com.mybatis.dao.UserDao.findUserByName", "小明");
        System.out.println(users);
        sqlSession.close();
    }
    @Test
    public void addUser()
    {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("kl");
        user.setBirthday(new Date());
        user.setSex("1");
        user.setAddress("shijiazhuang");
        sqlSession.insert("com.mybatis.dao.UserDao.addUser" , user);
        sqlSession.commit();
        System.out.print(user.getId());
        sqlSession.close();
    }
    @Test
    public void delUser()
    {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("com.mybatis.dao.UserDao.delUser" , "33");
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void updateUser()
    {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId("34");
        user.setUsername("ll");
        user.setAddress("xinle");
        sqlSession.update("com.mybatis.dao.UserDao.updateUser" , user);
        sqlSession.commit();
        sqlSession.close();

    }
}
