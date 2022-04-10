package per.meteor.pattern.builder.impl;

import per.meteor.pattern.builder.AbstractCarFactory;
import per.meteor.pattern.entity.Car;

/**
 * @author meteor
 * @date 2022-03-15 13:50
 */
public class SportCarFactory implements AbstractCarFactory {

    @Override
    public Car buildCar() {
        return new SimpleCarBuilder().setName("sport")
                .setSeats(2)
                .setColor("red")
                .setPrice(23d)
                .build();
    }
}
