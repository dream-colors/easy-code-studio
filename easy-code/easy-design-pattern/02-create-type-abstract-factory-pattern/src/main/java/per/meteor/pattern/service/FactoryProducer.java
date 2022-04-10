package per.meteor.pattern.service;

import java.util.logging.Logger;

/**
 * @author meteor
 * @date 2022-03-14 13:31
 */
public class FactoryProducer {

    private static final Logger LOGGER = Logger.getLogger(FactoryProducer.class.getName());

    public AbstractFactory getFactory(String factoryName) {
        String format = String.format("factoryName: %s", factoryName);
        LOGGER.info(format);
        AbstractFactory.FactoryType factoryType = AbstractFactory.FactoryType.valueOf(factoryName);
        switch (factoryType) {
            case SHAPE:
                return new ShapeFactory();
            case COLOR:
                return new ColorFactory();
            default:
                throw new IllegalArgumentException("ignore enum type");
        }
    }
}
