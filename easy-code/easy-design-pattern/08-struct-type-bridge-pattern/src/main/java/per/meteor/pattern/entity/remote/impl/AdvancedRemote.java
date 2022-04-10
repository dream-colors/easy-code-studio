package per.meteor.pattern.entity.remote.impl;

import per.meteor.pattern.entity.device.BaseDevice;

/**
 * @author meteor
 * @date 2022-03-15 18:19
 */
public class AdvancedRemote extends BasicRemote {

    public AdvancedRemote(BaseDevice device) {
        super.device = device;
    }

    public void mute() {
        System.out.println("Remote: mute");
        device.setVolume(0);
    }
}
