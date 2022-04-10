package per.meteor.pattern.entity;

import java.util.Objects;

/**
 * @author meteor
 * @date 2022-03-15 15:09
 */
public class Rectangle extends Shape {

    private Integer height;
    private Integer width;

    public  Rectangle(){}

    public Rectangle(Rectangle rectangle) {
        super(rectangle);
        if (rectangle != null) {
            this.height = rectangle.height;
            this.width = rectangle.width;
        }
    }

    @Override
    public Shape copy() {
        return new Rectangle(this);
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), height, width);
    }

    @Override
    public boolean equals(Object object2) {
        if (!(object2 instanceof Rectangle) || !super.equals(object2)) {
            return false;
        }
        Rectangle shape2 = (Rectangle) object2;
        return shape2.width.equals(width) && shape2.height.equals(height);
    }

}
