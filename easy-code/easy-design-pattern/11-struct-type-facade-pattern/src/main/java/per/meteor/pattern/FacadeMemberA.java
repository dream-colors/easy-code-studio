package per.meteor.pattern;

import java.util.logging.Logger;

/**
 * @author meteor
 * @date 2022-03-16 20:09
 */
public class FacadeMemberA {

    private static final Logger LOGGER = Logger.getLogger(FacadeMemberA.class.getName());

    public void method() {
        LOGGER.info("do facade member a method");
    }
}
