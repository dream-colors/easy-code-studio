package per.meteor.springboot.mapstruct.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import per.meteor.springboot.entity.Post;
import per.meteor.springboot.entity.User;
import per.meteor.springboot.mapstruct.dto.UserDto;

/**
 * componentModel指定映射模式，默认类名获取
 * @author meteor
 * @date 2022-03-18 16:59
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "post.postName", target = "post")
    UserDto toUserDto(User user, Post post);

    User toUser(UserDto userDto);
}
