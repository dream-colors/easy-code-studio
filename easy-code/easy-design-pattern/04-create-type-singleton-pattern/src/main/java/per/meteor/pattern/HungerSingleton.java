package per.meteor.pattern;

/**
 * 饿汉模式（线程安全）
 * 缺点：类加载就创建了，若实例未被使用，容易造成资源浪费
 * @author meteor
 * @date 2022-03-14 20:32
 */
public class HungerSingleton {

    private static final HungerSingleton INSTANCE = new HungerSingleton();

    private HungerSingleton() {
    }

    public static HungerSingleton getInstance() {
        return INSTANCE;
    }
}
