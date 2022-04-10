package per.meteor.pattern.entity;

import java.util.Objects;

/**
 * @author meteor
 * @date 2022-03-15 15:20
 */
public class Circle extends Shape {

    private Integer radius;

    public Circle() {

    }

    public Circle(Circle circle) {
        super(circle);
        if (circle != null) {
            this.radius = circle.radius;
        }
    }

    @Override
    public Shape copy() {
        return new Circle(this);
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), radius);
    }

    @Override
    public boolean equals(Object object2) {
        if (!(object2 instanceof Circle) || !super.equals(object2)) {
            return false;
        }
        Circle shape2 = (Circle) object2;
        return shape2.radius.equals(radius);
    }
}
