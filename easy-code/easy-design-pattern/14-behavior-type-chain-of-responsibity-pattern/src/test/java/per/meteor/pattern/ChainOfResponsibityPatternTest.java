package per.meteor.pattern;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author meteor
 * @date 2022-03-17 15:39
 */
class ChainOfResponsibityPatternTest {

    @Test
    void test() {
        FirstHandler firstHandler = new FirstHandler();
        SecondHandler secondHandler = new SecondHandler();
        ThiredHandler thiredHandler = new ThiredHandler();

        firstHandler.setNext(secondHandler);
        secondHandler.setNext(thiredHandler);

        HashMap<String, Object> params = new HashMap<>(2);
        params.put("second", null);
        firstHandler.handle(params);
    }

}
