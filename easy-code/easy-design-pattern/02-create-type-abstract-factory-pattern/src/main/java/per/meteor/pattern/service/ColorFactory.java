package per.meteor.pattern.service;

import per.meteor.pattern.service.impl.Blue;
import per.meteor.pattern.service.impl.Green;
import per.meteor.pattern.service.impl.Red;

/**
 * @author meteor
 * @date 2022-03-14 13:27
 */
public class ColorFactory extends AbstractFactory{

    @Override
    public BaseShape getShape(BaseShape.ShapeType shapeType) {
        return null;
    }

    @Override
    public BaseColor getColor(BaseColor.ColorType colorType) {
        if (colorType == null) {
            return null;
        }

        switch (colorType) {
            case RED:
                return new Red();
            case GREEN:
                return new Green();
            case BLUE:
                return new Blue();
            default:
                throw new IllegalArgumentException("illege enum type");
        }
    }
}
