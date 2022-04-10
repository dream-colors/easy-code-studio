package per.meteor.pattern.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 * @author meteor
 * @date 2022-03-14 11:26
 */
class ShapeSimpleFactoryTest {

    @ParameterizedTest
    @EnumSource(value = ShapeSimpleFactory.ShapeType.class,
            mode = EnumSource.Mode.INCLUDE,
            names = { "RECTANGLE", "CIRCLE", "SQUARE" })
    void getShape(ShapeSimpleFactory.ShapeType shapeType) {
        BaseShape shape = ShapeSimpleFactory.getShape(shapeType);
        Assertions.assertNotNull(shape);
        shape.draw();
    }
}
