package per.meteor.pattern.service;

import per.meteor.pattern.service.impl.Circle;
import per.meteor.pattern.service.impl.Rectangle;
import per.meteor.pattern.service.impl.Square;

/**
 * @author meteor
 * @date 2022-03-14 10:36
 */
public class ShapeFactory extends AbstractFactory {

    @Override
    public BaseShape getShape(BaseShape.ShapeType shapeType) {
        if (shapeType == null) {
            return null;
        }

        switch (shapeType) {
            case RECTANGLE:
                return new Rectangle();
            case SQUARE:
                return new Square();
            case CIRCLE:
                return new Circle();
            default:
                throw new IllegalArgumentException("illege enum type");
        }
    }

    @Override
    public BaseColor getColor(BaseColor.ColorType colorType) {
        return null;
    }
}
