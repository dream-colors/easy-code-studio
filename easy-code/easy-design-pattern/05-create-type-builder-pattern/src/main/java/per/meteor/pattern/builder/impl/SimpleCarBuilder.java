package per.meteor.pattern.builder.impl;

import per.meteor.pattern.builder.AbstractCarBuilder;
import per.meteor.pattern.entity.Car;

/**
 * @author meteor
 * @date 2022-03-15 13:18
 */
public class SimpleCarBuilder implements AbstractCarBuilder {

    private Car car;

    public SimpleCarBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.car = new Car();
    }

    @Override
    public AbstractCarBuilder setSeats(Integer seats) {
        this.car.setSeat(seats);
        return this;
    }

    @Override
    public AbstractCarBuilder setName(String name) {
        this.car.setName(name);
        return this;
    }

    @Override
    public AbstractCarBuilder setColor(String color) {
        this.car.setColor(color);
        return this;
    }

    @Override
    public AbstractCarBuilder setPrice(Double price) {
        this.car.setPrice(price);
        return this;
    }

    @Override
    public Car build() {
        return this.car;
    }
}
