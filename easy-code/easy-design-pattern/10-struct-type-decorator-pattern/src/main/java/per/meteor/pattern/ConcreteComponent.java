package per.meteor.pattern;

import java.util.logging.Logger;

/**
 * @author meteor
 * @date 2022-03-16 19:04
 */
public class ConcreteComponent implements BaseComponent {

    private static final Logger LOGGER = Logger.getLogger(ConcreteComponent.class.getName());

    @Override
    public void operation() {
        LOGGER.info("concrete component operation");
    }
}
