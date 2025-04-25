package home.deviceService;

import home.device.Device;
import home.device.Light;
import home.device.Thermostat;

public class ThermostatService {
    public static void addThermostat(String name, String protocol) {
        Thermostat thermostat = new Thermostat(name, protocol);

        Device.allDevices.add(thermostat);
    }

    public static void setTemperature(String name, int temperature) {
        Thermostat thermostat = toThermostat(name);

        thermostat.setTemperature(temperature);
    }

    public static Thermostat toThermostat(String name) {
        for (Device device: Device.allDevices)
            if (device.getName().equalsIgnoreCase(name))
                if (!(device instanceof Thermostat))
                    throw new IllegalArgumentException("this name doesn't belong to a light");
                else
                    return (Thermostat) device;
        throw new IllegalArgumentException("device not found");
    }
}
