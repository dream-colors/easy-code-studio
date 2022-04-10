package per.meteor.pattern.service;

import per.meteor.pattern.service.impl.Circle;
import per.meteor.pattern.service.impl.Rectangle;
import per.meteor.pattern.service.impl.Square;

/**
 * @author meteor
 * @date 2022-03-14 10:36
 */
public class ShapeSimpleFactory {

    public enum ShapeType {
        /** circle type */
        CIRCLE,
        /** rectangle type */
        RECTANGLE,
        /** square type */
        SQUARE
    }

    public static BaseShape getShape(ShapeType type) {
        if (type == null) {
            return null;
        }
        switch (type) {
            case RECTANGLE:
                return new Rectangle();
            case SQUARE:
                return new Square();
            case CIRCLE:
                return new Circle();
            default:
                throw new IllegalArgumentException("illegal enum shape type");
        }
    }

}
