package per.meteor.pattern.builder;

import per.meteor.pattern.entity.Car;

/**
 * @author meteor
 * @date 2022-03-15 13:13
 */
public interface AbstractCarBuilder {

    /**
     * 重置，初始化车辆信息
     */
    void reset();

    /**
     * seats
     * @param seats /
     * @return  /
     */
    AbstractCarBuilder setSeats(Integer seats);

    /**
     * 设置车辆名称
     * @param name  /
     * @return  /
     */
    AbstractCarBuilder setName(String name);

    /**
     * 设置车辆颜色
     * @param color /
     * @return  /
     */
    AbstractCarBuilder setColor(String color);

    /**
     * 设置车辆价格
     * @param price /
     * @return  /
     */
    AbstractCarBuilder setPrice(Double price);

    /**
     * 获取构建后的车辆信息
     * @return /
     */
    Car build();
}
