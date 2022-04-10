package per.metepr.pattern.entity.square;

import org.junit.jupiter.api.Test;
import per.metepr.pattern.entity.round.RoundHole;
import per.metepr.pattern.entity.round.RoundPeg;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author meteor
 * @date 2022-03-15 17:16
 */
class SquarePegAdapterTest {

    @Test
    void test() {
        RoundHole roundHole = new RoundHole(5d);
        RoundPeg roundPeg = new RoundPeg(5d);

        assertTrue(roundHole.fits(roundPeg));

        SquarePeg squarePeg1 = new SquarePeg(2d);
        SquarePeg squarePeg2 = new SquarePeg(20d);
        SquarePegAdapter squarePegAdapter1 = new SquarePegAdapter(squarePeg1);
        SquarePegAdapter squarePegAdapter2 = new SquarePegAdapter(squarePeg2);

        assertTrue(roundHole.fits(squarePegAdapter1));
        assertFalse(roundHole.fits(squarePegAdapter2));

    }
}
