package per.meteor.pattern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author meteor
 * @date 2022-03-16 22:11
 */
class FlyweightPatternTest {

    @Test
    void flyweightPatternTest() {
        FlyweightFactory flyweightFactory = FlyweightFactory.getInstance();
        SharedState sharedState = new SharedState("tom", 12);
        AbstractFlyweight objectA1 = flyweightFactory.get("objectA");
        AbstractFlyweight objectA2 = flyweightFactory.get("objectA");
        objectA1.operation(sharedState);
        objectA2.operation(sharedState);
        assertEquals(objectA1, objectA2);
        objectA1.operation(new SharedState("ailen", 13));
    }

}
