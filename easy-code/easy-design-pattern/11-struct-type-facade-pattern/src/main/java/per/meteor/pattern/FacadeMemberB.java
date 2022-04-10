package per.meteor.pattern;

import java.util.logging.Logger;

/**
 * @author meteor
 * @date 2022-03-16 20:09
 */
public class FacadeMemberB {

    private static final Logger LOGGER = Logger.getLogger(FacadeMemberB.class.getName());

    public void method() {
        LOGGER.info("do facade member b method");
    }
}
