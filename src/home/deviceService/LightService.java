package home.deviceService;

import home.device.Device;
import home.device.Light;

public class LightService {
    public void addLight(String name, String protocol) {
        Light light = new Light(name, protocol);

        Device.allDevices.add(light);
    }

    public void setBrightness(String name, int brightness) {
        Light light = toLight(name);

        light.setBrightness(brightness);
    }

    public Light toLight(String name) {
        for (Device device: Device.allDevices)
            if (device.getName().equalsIgnoreCase(name))
                if (!(device instanceof Light))
                    throw new IllegalArgumentException("this name doesn't belong to a light");
                else
                    return (Light) device;
        throw new IllegalArgumentException("device not found");
    }
}
