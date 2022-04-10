package per.meteor.pattern;

/**
 * @author meteor
 * @date 2022-03-16 19:07
 */
public abstract class AbstractDecorator implements BaseComponent {

    private final BaseComponent component;

    protected AbstractDecorator(BaseComponent component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}
