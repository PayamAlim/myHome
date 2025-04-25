package home.deviceService;

import home.device.Device;
import home.device.Thermostat;

import java.util.ArrayList;

public class DeviceService {
    static void removeDevice(String name) {
        for (Device device: Device.allDevices)
            if(device.getName().equalsIgnoreCase(name))
                Device.allDevices.remove(device);
    }


    public static void turnOn(String name) {
        Device device = toDevice(name);

        device.setStatus(Device.Status.On);
    }

    public static void turnOff(String name) {
        Device device = toDevice(name);

        device.setStatus(Device.Status.Off);
    }

    static ArrayList<Device> getAllDevices() {
        return Device.allDevices;
    }

    private static Device toDevice(String deviceName) {
        for (Device device: Device.allDevices)
            if(device.getName().equalsIgnoreCase(deviceName))
                return device;
        throw new IllegalArgumentException("device not found");
    }
}
