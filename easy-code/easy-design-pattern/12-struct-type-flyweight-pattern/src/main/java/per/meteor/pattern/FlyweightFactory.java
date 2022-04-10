package per.meteor.pattern;

import java.util.concurrent.ConcurrentHashMap;

/**
 * flyweight factory
 * @author meteor
 * @date 2022-03-16 21:52
 */
public class FlyweightFactory {

    private static final ConcurrentHashMap<String, AbstractFlyweight> OBJECT_CONTEXT = new ConcurrentHashMap<>(16);

    private enum Singleton {
        /** singleton instance **/
        INSTANCE;

        private final FlyweightFactory instance;

        Singleton() {
            instance = new FlyweightFactory();
            this.instance.put("objectA", ConcreteFlyweightA.getInstance());
            this.instance.put("objectB", ConcreteFlyweightB.getInstance());
        }
    }

    public static FlyweightFactory getInstance() {
        return Singleton.INSTANCE.instance;
    }

    public void put(String name, AbstractFlyweight flyweight) {
        OBJECT_CONTEXT.put(name, flyweight);
    }

    public AbstractFlyweight get(String name) {
        return OBJECT_CONTEXT.get(name);
    }
}
