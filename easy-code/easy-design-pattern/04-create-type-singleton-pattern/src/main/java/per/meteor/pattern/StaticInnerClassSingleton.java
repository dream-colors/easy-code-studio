package per.meteor.pattern;

/**
 * 静态内部类
 * 利用加载类不会加载内部类的特点，只会在使用时进行加载
 * @author meteor
 * @date 2022-03-17 14:07
 */
public class StaticInnerClassSingleton {

    private static class SingletonHolder  {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    private StaticInnerClassSingleton(){}

    public static StaticInnerClassSingleton getInstance() {
        return  SingletonHolder.INSTANCE;
    }
}
