package per.meteor.pattern;

import java.util.logging.Logger;

/**
 * @author meteor
 * @date 2022-03-16 22:37
 */
public class ConcreteSubject implements BaseSubject {

    private static final Logger LOGGER = Logger.getLogger(ConcreteSubject.class.getName());

    @Override
    public void operation() {
        LOGGER.info("do concrete subject method");
    }
}
