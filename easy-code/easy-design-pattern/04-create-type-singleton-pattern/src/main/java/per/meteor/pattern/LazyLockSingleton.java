package per.meteor.pattern;

/**
 * 懒汉模式（线程安全）
 * 缺点：方法锁资源占用大
 * @author meteor
 * @date 2022-03-17 13:45
 */
public class LazyLockSingleton {

    private static LazyLockSingleton instance;

    private LazyLockSingleton(){}

    public static synchronized LazyLockSingleton getInstance() {
        if (instance == null) {
            instance = new LazyLockSingleton();
        }
        return instance;
    }

}
