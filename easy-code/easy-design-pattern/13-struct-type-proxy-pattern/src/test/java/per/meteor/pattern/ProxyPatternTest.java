package per.meteor.pattern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author meteor
 * @date 2022-03-16 22:42
 */
class ProxyPatternTest {

    @Test
    void proxyPatternTest() {
        new ProxySubject(new ConcreteSubject()).operation();
    }

}
