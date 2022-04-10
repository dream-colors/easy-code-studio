package per.meteor.pattern;

import java.util.logging.Logger;

/**
 * @author meteor
 * @date 2022-03-17 15:13
 */
public class SecondHandler extends AbstractHandler {

    private static final Logger LOGGER = Logger.getLogger(SecondHandler.class.getName());

    @Override
    public void doHandle() {
        LOGGER.info("do method with second handler");
    }

    @Override
    public boolean canHandle() {
        return params.containsKey("second");
    }
}
