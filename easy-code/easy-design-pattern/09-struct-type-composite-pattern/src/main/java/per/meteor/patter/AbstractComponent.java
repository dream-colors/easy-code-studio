package per.meteor.patter;

/**
 * 抽象组件：定义了访问和管理子组件的方
 */
abstract class AbstractComponent {

    /**
     * 添加组件
     *
     * @param component /
     */
    public abstract void add(AbstractComponent component);

    /**
     * 移除组件
     *
     * @param component /
     */
    public abstract void remove(AbstractComponent component);

    /**
     * 获取子组件
     *
     * @param i /
     * @return /
     */
    public abstract AbstractComponent getChild(int i);

    /**
     * 业务操作
     */
    public abstract void operation();
}
