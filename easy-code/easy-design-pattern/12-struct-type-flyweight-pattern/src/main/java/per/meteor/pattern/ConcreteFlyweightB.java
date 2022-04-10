package per.meteor.pattern;

import java.util.logging.Logger;

/**
 * @author meteor
 * @date 2022-03-16 20:55
 */
public class ConcreteFlyweightB implements AbstractFlyweight {

    private static final Logger LOGGER = Logger.getLogger(ConcreteFlyweightB.class.getName());

    private ConcreteFlyweightB(){}

    private enum Singleton {
        /** singleton instance **/
        INSTANCE;

        private final ConcreteFlyweightB instance;

        Singleton() {
            instance = new ConcreteFlyweightB();
        }

        private ConcreteFlyweightB getInstance() {
            return instance;
        }
    }

    public static ConcreteFlyweightB getInstance() {
        return Singleton.INSTANCE.getInstance();
    }


    @Override
    public void operation(SharedState sharedState) {
        LOGGER.info(sharedState.toString());
        LOGGER.info("concrete flyweight b do method");
    }
}
