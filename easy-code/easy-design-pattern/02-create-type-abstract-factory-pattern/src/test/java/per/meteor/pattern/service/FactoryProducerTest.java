package per.meteor.pattern.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.EnumSet;

/**
 * @author meteor
 * @date 2022-03-14 13:38
 */
class FactoryProducerTest {

    @ParameterizedTest
    @ValueSource(strings = { "SHAPE", "COLOR" })
    void getFactory(String factoryName) {
        AbstractFactory factory = new FactoryProducer().getFactory(factoryName);

        Assertions.assertNotNull(factory);

        switch (factoryName) {
            case "SHAPE":
                EnumSet.allOf(BaseShape.ShapeType.class).forEach(shapeType -> factory.getShape(shapeType).draw());
                break;
            case "COLOR":
                EnumSet.allOf(BaseColor.ColorType.class).forEach(colorType -> factory.getColor(colorType).fill());
                break;
        }
    }

}
