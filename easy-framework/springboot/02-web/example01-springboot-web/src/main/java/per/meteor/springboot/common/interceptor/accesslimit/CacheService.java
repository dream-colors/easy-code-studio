package per.meteor.springboot.common.interceptor.accesslimit;

/**
 * @author meteor
 */
public interface CacheService {

    /**
     * 获取缓存数据
     * @param key /
     * @return /
     */
    String get(String key);

    /**
     * 添加缓存数据
     * @param key /
     * @param value /
     * @param expire 过期时间, 秒
     */
    void set(String key, String value, long expire);

    /**
     * 自增1
     * @param key /
     */
    void incr(String key);
}
