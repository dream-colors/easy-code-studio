package per.metepr.pattern.entity.square;

/**
 * @author meteor
 * @date 2022-03-15 17:06
 */
public class SquarePeg {

    private Double width;

    public SquarePeg(Double radius) {
        this.width = radius;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double radius) {
        this.width = radius;
    }
}
