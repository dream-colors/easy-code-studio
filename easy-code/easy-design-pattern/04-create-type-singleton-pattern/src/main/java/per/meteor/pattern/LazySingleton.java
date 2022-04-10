package per.meteor.pattern;

/**
 * 懒汉模式（线程不安全）
 * 缺点：多线程首次访问下，容易重复创建
 * @author meteor
 * @date 2022-03-14 20:36
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

}
