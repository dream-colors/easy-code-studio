package per.meteor.pattern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author meteor
 * @date 2022-03-16 19:15
 */
class ConcreteDecoratorTest {

    @Test
    void decoratorPatternTest() {

        // create concrete component object
        ConcreteComponent concreteComponent = new ConcreteComponent();
        concreteComponent.operation();
        // create concrete decorator object
        ConcreteDecorator concreteDecorator = new ConcreteDecorator(concreteComponent);
        concreteDecorator.operation();

    }
}
