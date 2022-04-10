package per.meteor.patter;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * 容器节点
 * @author Administrator
 */
public class CompositeComponent extends AbstractComponent {

    private static final Logger LOGGER = Logger.getLogger(CompositeComponent.class.getName());
    private final ArrayList<AbstractComponent> components = new ArrayList<>();

    @Override
    public void add(AbstractComponent component) {
        components.add(component);
    }

    @Override
    public void remove(AbstractComponent component) {
        components.remove(component);
    }

    @Override
    public AbstractComponent getChild(int i) {
        return components.get(i);
    }

    @Override
    public void operation() {
        LOGGER.info("当前节点为容器节点");
        components.forEach(AbstractComponent::operation);
    }
}
