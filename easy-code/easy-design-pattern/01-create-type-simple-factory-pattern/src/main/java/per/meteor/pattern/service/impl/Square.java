package per.meteor.pattern.service.impl;

import per.meteor.pattern.service.BaseShape;

import java.util.logging.Logger;

/**
 * @author meteor
 * @date 2022-03-14 10:32
 */
public class Square implements BaseShape {

    public static final Logger LOGGER = Logger.getLogger(Square.class.getName());

    @Override
    public void draw() {
        LOGGER.info("Inside Square::draw() method");
    }
}

