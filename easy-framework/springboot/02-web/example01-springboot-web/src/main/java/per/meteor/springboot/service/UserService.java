package per.meteor.springboot.service;

import per.meteor.springboot.entity.User;

/**
 * @author meteor
 * @date 2022-03-19 17:00
 */
public interface UserService {

    User get(Integer id);

    boolean add(User user);

    boolean update(User user);

    boolean delete(Integer id);

}
