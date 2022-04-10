package per.meteor.pattern.entity.device;

/**
 * @author meteor
 * @date 2022-03-15 18:07
 */
public interface BaseDevice {

    /**
     * -
     * @return /
     */
    boolean isEnabled();

    /**
     * -
     */
    void enable();

    /**
     * -
     */
    void disable();

    /**
     * -
     * @return /
     */
    int getVolume();

    /**
     * -
     * @param percent /
     */
    void setVolume(int percent);

    /**
     * -
     * @return /
     */
    int getChannel();

    /**
     * -
     * @param channel /
     */
    void setChannel(int channel);

    /**
     * -
     */
    void printStatus();

}
