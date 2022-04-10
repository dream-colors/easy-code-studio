package per.metepr.pattern.entity.square;

import per.metepr.pattern.entity.round.RoundPeg;

/**
 * @author meteor
 * @date 2022-03-15 17:08
 */
public class SquarePegAdapter extends RoundPeg {

    private final SquarePeg squarePeg;

    public SquarePegAdapter(SquarePeg squarePeg) {
        this.squarePeg = squarePeg;
    }

    @Override
    public Double getRadius() {
        return Math.sqrt(Math.pow((squarePeg.getWidth() / 2), 2) * 2);
    }
}
