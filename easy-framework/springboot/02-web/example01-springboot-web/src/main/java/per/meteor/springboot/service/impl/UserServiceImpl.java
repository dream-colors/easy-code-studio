package per.meteor.springboot.service.impl;

import org.springframework.stereotype.Service;
import per.meteor.springboot.entity.User;
import per.meteor.springboot.repository.UserRepository;
import per.meteor.springboot.service.UserService;

/**
 * @author meteor
 * @date 2022-03-19 17:02
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(Integer id) {
        return userRepository.get(id);
    }

    @Override
    public boolean add(User user) {
        return userRepository.add(user);
    }

    @Override
    public boolean update(User user) {
        return userRepository.update(user);
    }

    @Override
    public boolean delete(Integer id) {
        return userRepository.delete(id);
    }
}
