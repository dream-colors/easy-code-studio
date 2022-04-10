package per.meteor.pattern;

/**
 * @author meteor
 * @date 2022-03-16 20:26
 */
public interface AbstractFlyweight {

    /**
     * do different method logic
     * @param sharedState /
     */
    void operation(SharedState sharedState);
}
