package per.meteor.pattern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author meteor
 * @date 2022-03-16 20:14
 */
class FacadePatternTest {

    @Test
    void facadePatternTest() {
        Facade facade = new Facade();
        facade.method();
    }
}
