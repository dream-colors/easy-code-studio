package per.meteor.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import per.meteor.springboot.entity.User;
import per.meteor.springboot.service.UserService;

/**
 *
 * @author meteor
 * @date 2022-03-19 17:05
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> get(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.get(userId));
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody User user) {

        return userService.add(user) ? ResponseEntity.ok("添加成功") : ResponseEntity.status(500).body("添加失败");
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody User user) {

        return userService.update(user) ? ResponseEntity.ok("更新成功") : ResponseEntity.status(500).body("更新失败");
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(Integer userId) {

        return userService.delete(userId) ? ResponseEntity.ok("删除成功") : ResponseEntity.status(500).body("删除失败");
    }
}
