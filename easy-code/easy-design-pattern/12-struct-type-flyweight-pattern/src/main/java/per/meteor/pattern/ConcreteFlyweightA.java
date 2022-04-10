package per.meteor.pattern;

import java.util.logging.Logger;

/**
 * @author meteor
 * @date 2022-03-16 20:55
 */
public class ConcreteFlyweightA implements AbstractFlyweight {

    private static final Logger LOGGER = Logger.getLogger(ConcreteFlyweightA.class.getName());

    private ConcreteFlyweightA(){}

    private enum Singleton {
        /** singleton instance **/
        INSTANCE;

        private final ConcreteFlyweightA instance;

        Singleton() {
            instance = new ConcreteFlyweightA();
        }

        private ConcreteFlyweightA getInstance() {
            return instance;
        }
    }

    public static ConcreteFlyweightA getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    @Override
    public void operation(SharedState sharedState) {
        LOGGER.info(sharedState.toString());
        LOGGER.info("concrete flyweight a do method");
    }
}
