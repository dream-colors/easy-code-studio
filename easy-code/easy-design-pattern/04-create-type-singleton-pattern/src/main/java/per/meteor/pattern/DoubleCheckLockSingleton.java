package per.meteor.pattern;

/**
 * 双检锁（线程安全）
 * 减少获取实例的耗时
 * @author meteor
 * @date 2022-03-17 13:53
 */
public class DoubleCheckLockSingleton {

    private static volatile DoubleCheckLockSingleton instance;

    private DoubleCheckLockSingleton() {}

    public static DoubleCheckLockSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckLockSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckLockSingleton();
                }
            }
        }
        return instance;
    }

}
