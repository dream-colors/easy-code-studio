package per.meteor.pattern.entity;

import java.util.Objects;

/**
 * @author meteor
 * @date 2022-03-15 14:59
 */
public abstract class Shape {

    private Integer x = 0;
    private Integer y = 0;
    private String color = "";

    protected Shape(){

    }

    protected Shape(Shape shape) {
        if (shape != null) {
            this.x = shape.getX();
            this.y = shape.getY();
            this.color = shape.getColor();
        }
    }

    /**
     * 原型核心方法
     * @return /
     */
    public abstract Shape copy();

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object object2) {
        if (!(object2 instanceof Shape)) {
            return false;
        }
        Shape shape2 = (Shape) object2;
        return shape2.x.equals(x) && shape2.y.equals(y) && Objects.equals(shape2.color, color);
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
