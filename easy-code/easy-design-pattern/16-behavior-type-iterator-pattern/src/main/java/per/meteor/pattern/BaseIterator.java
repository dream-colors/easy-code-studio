package per.meteor.pattern;

/**
 * @author meteor
 * @date 2022-03-17 17:43
 */
public interface BaseIterator {

    /**
     * is first element
     */
    void first();

    /**
     * to ponint first element
     */
    void next();

    /**
     * has next element
     * @return  /
     */
    boolean hasNext();

    /**
     * current element
     * @return  /
     */
    Object currentItem();

}
