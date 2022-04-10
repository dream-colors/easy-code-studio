package per.meteor.pattern;

/**
 * 枚举（贤臣安全）
 * 防止了自由串行化
 * @author meteor
 * @date 2022-03-17 14:10
 */
public class EnumSingleton {

    private enum SingletonHolder {
        /** enum */
        INSTANCE;

        private final EnumSingleton instance;

        SingletonHolder() {
            instance = new EnumSingleton();
        }
    }

    private EnumSingleton(){}

    public static EnumSingleton getInstance() {
        return SingletonHolder.INSTANCE.instance;
    }
}
