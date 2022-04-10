package per.meteor.pattern.service;

/**
 * @author meteor
 * @date 2022-03-14 13:11
 */
public interface BaseColor {

    enum ColorType {
        /** red */
        RED,
        /** green */
        GREEN,
        /** blue */
        BLUE
    }

    /**
     * fill
     */
    void fill();
}
