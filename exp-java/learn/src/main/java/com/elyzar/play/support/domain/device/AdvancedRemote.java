package com.elyzar.play.support.domain.device;

/**
 * You can develop the remote control classes independently from the device classes. All that’s needed is to create a new remote subclass.
 * For example, a basic remote control might only have two buttons, but you could extend it with additional features, such as an extra battery or a touchscreen.
 */
public class AdvancedRemote extends BasicRemote {

    public AdvancedRemote(Device device) {
        super.device = device;
    }

    public void mute() {
        System.out.println("Remote: mute");
        device.setVolume(0);
    }
}
