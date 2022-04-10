package per.meteor.pattern.service;

/**
 * @author meteor
 * @date 2022-03-14 13:16
 */
public abstract class AbstractFactory {

    enum FactoryType {
        /** shape type */
        SHAPE,
        /** color type */
        COLOR,
    }

    /**
     * get shape
     * @param shapeType /
     * @return /
     */
    public abstract BaseShape getShape(BaseShape.ShapeType shapeType);

    /**
     * get color
     * @param colorType /
     * @return /
     */
    public abstract BaseColor getColor(BaseColor.ColorType colorType);
}
