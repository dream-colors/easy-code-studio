package per.meteor.pattern;

import org.junit.jupiter.api.Test;
import per.meteor.pattern.entity.device.BaseDevice;
import per.meteor.pattern.entity.device.impl.Radio;
import per.meteor.pattern.entity.device.impl.Tv;
import per.meteor.pattern.entity.remote.impl.AdvancedRemote;
import per.meteor.pattern.entity.remote.impl.BasicRemote;

/**
 * @author meteor
 * @date 2022-03-15 18:20
 */
class BridgePatternTest {

    @Test
    void test() {
        testDevice(new Tv());
        testDevice(new Radio());
    }

    public static void testDevice(BaseDevice device) {
        System.out.println("Tests with basic remote.");
        BasicRemote basicRemote = new BasicRemote(device);
        basicRemote.power();
        device.printStatus();

        System.out.println("Tests with advanced remote.");
        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.power();
        advancedRemote.mute();
        device.printStatus();
    }
}
