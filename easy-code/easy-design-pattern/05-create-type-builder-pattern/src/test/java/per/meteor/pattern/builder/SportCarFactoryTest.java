package per.meteor.pattern.builder;

import org.junit.jupiter.api.Test;
import per.meteor.pattern.builder.impl.SportCarFactory;
import per.meteor.pattern.entity.Car;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author meteor
 * @date 2022-03-15 13:43
 */
class SportCarFactoryTest {

    @Test
    void buildSportsCar() {

        Car car = new SportCarFactory().buildCar();
        assertNotNull(car);
        System.out.println(car);
    }
}
