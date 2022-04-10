package per.metepr.pattern.entity.round;

/**
 * @author meteor
 * @date 2022-03-15 17:03
 */
public class RoundHole {

    private Double radius;

    public RoundHole(){}

    public RoundHole(Double radius) {
        this.radius = radius;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public boolean fits(RoundPeg roundPeg) {
        return this.radius >= roundPeg.getRadius();
    }
}
