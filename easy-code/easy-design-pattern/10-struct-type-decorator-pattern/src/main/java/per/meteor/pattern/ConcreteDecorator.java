package per.meteor.pattern;

import java.util.logging.Logger;

/**
 * @author meteor
 * @date 2022-03-16 19:12
 */
public class ConcreteDecorator extends AbstractDecorator {

    private static final Logger LOGGER = Logger.getLogger(ConcreteDecorator.class.getName());

    protected ConcreteDecorator(BaseComponent component) {
        super(component);
    }

    @Override
    public void operation() {
        doDecorate();
        super.operation();
    }

    private void doDecorate() {
        LOGGER.info("concrete decorator do decorate");
    }
}
