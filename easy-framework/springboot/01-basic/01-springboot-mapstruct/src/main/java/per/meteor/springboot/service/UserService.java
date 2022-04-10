package per.meteor.springboot.service;

import org.springframework.stereotype.Service;
import per.meteor.springboot.entity.Post;
import per.meteor.springboot.entity.User;
import per.meteor.springboot.mapstruct.converter.UserConverter;
import per.meteor.springboot.mapstruct.dto.UserDto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author meteor
 * @date 2022-03-18 18:46
 */
@Service
public class UserService {

    private final UserConverter userConverter;
    private static final Map<String, User> USERS = new HashMap<>();
    private static final Set<Post> POSTS = new HashSet<>();

    public UserService(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    public UserDto select(String userName) {
        User user = USERS.getOrDefault(userName, null);
        Post post = POSTS.stream().filter(item -> item.getUserId().equals(user.getId())).findFirst().orElse(null);
        return userConverter.toUserDto(user, post);
    }

    public void putUser(UserDto userDto) {
        USERS.put(userDto.getName(), userConverter.toUser(userDto));
    }

    public void addPost(Post post) {
        POSTS.add(post);
    }
}
