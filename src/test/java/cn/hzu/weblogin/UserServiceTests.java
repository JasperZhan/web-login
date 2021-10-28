package cn.hzu.weblogin;

import cn.hzu.weblogin.model.Result;
import cn.hzu.weblogin.model.User;
import cn.hzu.weblogin.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Resource
    UserService userService;

    @Test
    public void registerByTelTest() {
        User user = new User();
        user.setTel("18124993036");
        user.setPassword("654321");
        Result<User> result = null;
        result = userService.registerByTel(user);
        result.printResult();
    }

    @Test
    public void registerByMailTest() {
        User user = new User();
        user.setEmail("zhangli@edu.hzu.cn");
        user.setPassword("654321");
        Result<User> result = null;
        result = userService.registerByMail(user);
        result.printResult();
    }
}
