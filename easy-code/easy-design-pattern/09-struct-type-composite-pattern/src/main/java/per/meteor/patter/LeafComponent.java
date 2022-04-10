package per.meteor.patter;

import java.util.logging.Logger;

/**
 * 叶子节点
 * @author Administrator
 */
public class LeafComponent extends AbstractComponent {

    private static final Logger LOGGER = Logger.getLogger(LeafComponent.class.getName());

    @Override
    public void add(AbstractComponent component) {
        throw new UnsupportedOperationException("当前节点为叶子节点， 无法添加叶子节点");
    }

    @Override
    public void remove(AbstractComponent component) {
        throw new UnsupportedOperationException("当前节点为叶子节点。无法删除叶子节点");
    }

    @Override
    public AbstractComponent getChild(int i) {
        throw new UnsupportedOperationException("当前节点为叶子节点");
    }

    @Override
    public void operation() {
        LOGGER.info("当前节点为叶子节点");
    }
}
