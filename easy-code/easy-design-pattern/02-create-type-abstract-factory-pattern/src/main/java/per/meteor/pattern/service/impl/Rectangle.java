package per.meteor.pattern.service.impl;

import per.meteor.pattern.service.BaseShape;

import java.util.logging.Logger;

/**
 * @author meteor
 * @date 2022-03-14 10:30
 */
public class Rectangle implements BaseShape {

    public static final Logger LOGGER = Logger.getLogger(Rectangle.class.getName());

    @Override
    public void draw() {
        LOGGER.info("Inside Rectangle::draw() methord");
    }
}
