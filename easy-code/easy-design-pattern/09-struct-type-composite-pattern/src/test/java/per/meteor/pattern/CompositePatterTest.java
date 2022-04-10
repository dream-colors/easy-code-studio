package per.meteor.pattern;

import org.junit.jupiter.api.Test;
import per.meteor.patter.CompositeComponent;
import per.meteor.patter.LeafComponent;

/**
 * @author meteor
 * @date 2022-03-16 17:11
 */
class CompositePatterTest {

    @Test
    void test() {
        CompositeComponent compositeComponent = new CompositeComponent();
        compositeComponent.add(new CompositeComponent());
        compositeComponent.add(new CompositeComponent());
        LeafComponent leafComponent = new LeafComponent();
        compositeComponent.add(leafComponent);
        compositeComponent.operation();
    }
}
