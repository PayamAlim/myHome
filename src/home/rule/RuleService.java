package home.rule;

import home.device.Device;
import home.deviceService.DeviceService;

import java.util.ArrayList;

public class RuleService {
    public static void addRule(String deviceName, String time, boolean action) {
        Rule rule = new Rule(toDevice(deviceName), time, action);

        Rule.allRules.add(rule);
    }

    public static void checkRules(String time) {
        for (Rule rule: Rule.allRules)
            if (rule.getTime().equals(time))
                doRule(rule, rule.getAction());
    }

    private static void doRule(Rule rule, boolean action) {
        if (action)
            DeviceService.turnOn(rule.getDeviceRef().getName());
        else
            DeviceService.turnOff(rule.getDeviceRef().getName());
    }

    private static Device toDevice(String deviceName) {
        for (Device device: Device.allDevices)
            if(device.getName().equalsIgnoreCase(deviceName))
                return device;
        throw new IllegalArgumentException("device not found");
    }
}
