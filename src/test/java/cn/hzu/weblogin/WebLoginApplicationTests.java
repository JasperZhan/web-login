package cn.hzu.weblogin;

import cn.hzu.weblogin.dao.UserDao;
import cn.hzu.weblogin.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
class WebLoginApplicationTests {

  @Resource
  UserDao userDao;

  User user = new User();

  @Test
  void contextLoads() {
    user = userDao.getByPhone("13202182025");
    System.out.println(user);
  }

  @Test
  void testUpdate() {
    user.setUserId(1);
    user.setTel("13212341234");
    user.setPassword("111111");
    int i = userDao.update(user);

  }

  @Test
  void testInsert() {
    user.setTel("18144601204");
    user.setPassword("1111111");
    userDao.add(user);
  }
}
