package per.meteor.springboot.repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.meteor.springboot.entity.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author meteor
 * @date 2022-03-19 16:44
 */
@Repository
public class UserRepository {

    private static final Map<Integer, User> LOCAL_DATA_MAP = new HashMap<>(16);

    public User get(Integer userId) {
        return LOCAL_DATA_MAP.getOrDefault(userId, null);
    }

    public Collection<User> selectAll() {
        return LOCAL_DATA_MAP.values();
    }

    public boolean add(User user) {
        if (user == null || user.getId() == null){
            return false;
        }
        return ObjectUtils.nullSafeEquals(user, LOCAL_DATA_MAP.putIfAbsent(user.getId(), user));
    }

    public boolean update(User user) {
        return add(user);
    }

    public boolean delete(Integer id) {
        if (LOCAL_DATA_MAP.containsKey(id)) {
            return LOCAL_DATA_MAP.remove(id, get(id));
        }

        return false;
    }
}
