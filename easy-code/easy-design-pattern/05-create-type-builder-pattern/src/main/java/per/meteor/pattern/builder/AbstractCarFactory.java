package per.meteor.pattern.builder;

import per.meteor.pattern.entity.Car;

/**
 * @author meteor
 * @date 2022-03-15 13:35
 */
public interface AbstractCarFactory {

    /**
     * 构建指定车辆
     * @return /
     */
    Car buildCar();
}
