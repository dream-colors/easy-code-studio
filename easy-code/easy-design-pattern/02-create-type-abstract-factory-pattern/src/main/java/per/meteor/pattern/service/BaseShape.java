package per.meteor.pattern.service;

/**
 * @author meteor
 * @date 2022-03-14 10:28
 */
public interface BaseShape {

    enum ShapeType {
        /** circle type */
        CIRCLE,
        /** rectangle type */
        RECTANGLE,
        /** square type */
        SQUARE
    }

    /**
     * draw
     */
    void draw();
}


