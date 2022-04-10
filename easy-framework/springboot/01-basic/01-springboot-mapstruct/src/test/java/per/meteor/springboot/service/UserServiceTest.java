package per.meteor.springboot.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import per.meteor.springboot.entity.Post;
import per.meteor.springboot.mapstruct.dto.UserDto;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author meteor
 * @date 2022-03-18 18:52
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @BeforeEach
    void init() {
        userService.putUser(new UserDto()
                .setId(1)
                .setName("tom")
                .setPassword("123")
                .setBirthday(LocalDateTime.now()));

        userService.addPost(new Post()
                .setId(2)
                .setUserId(1)
                .setPostName("student")
                .setSalary(100d));
    }

    @Test
    void select() {
        UserDto tom = userService.select("tom");
        Assertions.assertEquals(1, tom.getId());
        Assertions.assertEquals("student", tom.getPost());
        System.out.println(tom);
    }
}
