package per.meteor.pattern.service.impl;

import per.meteor.pattern.service.BaseColor;

import java.util.logging.Logger;

/**
 * @author meteor
 * @date 2022-03-14 13:12
 */
public class Green implements BaseColor {

    public static final Logger LOGGER = Logger.getLogger(Green.class.getName());

    @Override
    public void fill() {
        LOGGER.info("Inside Green::fill() method");
    }
}
