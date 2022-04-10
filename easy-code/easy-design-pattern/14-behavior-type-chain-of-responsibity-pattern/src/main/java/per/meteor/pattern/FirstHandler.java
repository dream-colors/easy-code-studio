package per.meteor.pattern;

import java.util.logging.Logger;

/**
 * @author meteor
 * @date 2022-03-17 15:13
 */
public class FirstHandler extends AbstractHandler {

    private static final Logger LOGGER = Logger.getLogger(FirstHandler.class.getName());

    @Override
    public void doHandle() {
        LOGGER.info("do method with first handler");
    }

    @Override
    public boolean canHandle() {
        return params.containsKey("first");
    }
}
