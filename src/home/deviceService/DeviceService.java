package home.deviceService;

import home.device.Device;

import java.util.ArrayList;

public class DeviceService {
    static void removeDevice(String name) {
        for (Device device: Device.allDevices)
            if(device.getName().equalsIgnoreCase(name))
                Device.allDevices.remove(device);
    }

    static ArrayList<Device> getAllDevices() {
        return Device.allDevices;
    }
}
