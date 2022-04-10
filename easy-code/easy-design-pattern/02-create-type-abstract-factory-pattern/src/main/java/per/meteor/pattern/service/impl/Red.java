package per.meteor.pattern.service.impl;

import per.meteor.pattern.service.BaseColor;

import java.util.logging.Logger;

/**
 * @author meteor
 * @date 2022-03-14 13:12
 */
public class Red implements BaseColor {

    public static final Logger LOGGER = Logger.getLogger(Red.class.getName());

    @Override
    public void fill() {
        LOGGER.info("Inside Read::fill() method");
    }
}
