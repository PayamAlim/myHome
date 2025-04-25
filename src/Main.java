import home.device.Device;
import home.device.Light;
import home.device.Thermostat;
import home.deviceService.DeviceService;
import home.deviceService.LightService;
import home.deviceService.ThermostatService;
import home.rule.Rule;
import home.rule.RuleService;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Device.allProtocols.add("wifi");
        Device.allProtocols.add("bluetooth");

        HashMap<String, Device> deviceType = new HashMap<>();
        deviceType.put("light", new Light("light", "wifi"));
        deviceType.put("thermostat", new Thermostat("thermostat", "wifi"));

        var scn = new Scanner(System.in);

        int q = scn.nextInt();
        while (q -- > 0) {
            String command = scn.next();

            if (command.equalsIgnoreCase("add_device")) {
                String type = scn.next();

                String name = scn.next();

                String protocol = scn.next();

                type = type.toLowerCase();
                if (deviceType.get(type) == null) {
                    System.out.println("invalid input");
                    continue;
                }

                try {
                    if (deviceType.get(type) instanceof Light)
                        LightService.addLight(name, protocol);
                    else
                        ThermostatService.addThermostat(name, protocol);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                System.out.println("device added successfully");
            } else if (command.equalsIgnoreCase("set_device")) {
                String name = scn.next();

                String property = scn.next();

                String value = scn.next();

                Device device = null;
                try {
                    device = DeviceService.toDevice(name);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                property = property.toLowerCase();
                if (!device.properties.contains(property)) {
                    System.out.println("invalid property");
                    continue;
                }

                try {
                    if (property.equals("status"))
                        if (value.equalsIgnoreCase("on"))
                            DeviceService.turnOn(device.getName());
                        else if (value.equalsIgnoreCase("off"))
                            DeviceService.turnOff(device.getName());
                        else
                            System.out.println("invalid value");
                    else if (property.equals("brightness"))
                        LightService.setBrightness(device.getName(), Integer.parseInt(value));
                    else if (property.equals("temperature"))
                        ThermostatService.setTemperature(device.getName(), Integer.parseInt(value));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                System.out.println("device updated successfully");
            } else if (command.equalsIgnoreCase("remove_device")) {
                String name = scn.next();

                Device device = null;
                try {
                    device = DeviceService.toDevice(name);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                Device.allDevices.remove(device);
                for (int i = 0; i < Rule.allRules.size(); i ++) {
                    Rule rule = Rule.allRules.get(i);
                    if (rule.getDeviceRef().getName().equalsIgnoreCase(device.getName()))
                        Rule.allRules.remove(rule);
                }

                System.out.println("device removed successfully");
            } else if (command.equalsIgnoreCase("list_devices")) {
                for (Device device: Device.allDevices) {
                    System.out.print(device.getName() + " " + device.getStatus());
                    if (device instanceof Light)
                        System.out.print(" " + ((Light) device).getBrightness() + "%");
                    else
                        System.out.print(" " + ((Thermostat) device).getTemperature());
                    System.out.println(" " + device.getProtocol());
                }
            } else if (command.equalsIgnoreCase("add_rule")) {
                String name = scn.next();

                String time = scn.next();

                String action = scn.next();

                Device device = null;
                try {
                    device = DeviceService.toDevice(name);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                if (!time.contains(":")) {
                    System.out.println("invalid time");
                    continue;
                }
                try {
                    String[] parts = time.split(":");
                    int hours = Integer.parseInt(parts[0]);
                    int minutes = Integer.parseInt(parts[1]);

                    if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
                        System.out.println("invalid time");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("invalid time");
                    continue;
                }

                if (action.equalsIgnoreCase("on"))
                    RuleService.addRule(device.getName(), time, true);
                else if (action.equalsIgnoreCase("off"))
                    RuleService.addRule(device.getName(), time, false);
                else {
                    System.out.println("invalid action");
                    continue;
                }

                System.out.println("rule added successfully");
            } else if (command.equalsIgnoreCase("check_rules")) {
                String time = scn.next();
                if (!time.contains(":")) {
                    System.out.println("invalid time");
                    continue;
                }
                try {
                    String[] parts = time.split(":");
                    int hours = Integer.parseInt(parts[0]);
                    int minutes = Integer.parseInt(parts[1]);

                    if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
                        System.out.println("invalid time");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("invalid time");
                    continue;
                }

                try {
                    RuleService.checkRules(time);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                System.out.println("rules checked");
            } else if (command.equalsIgnoreCase("list_rules")) {
                if (Rule.allRules.isEmpty())
                    System.out.println();
                else {
                    for (Rule rule : Rule.allRules) {
                        System.out.print(rule.getDeviceRef().getName() + " " + rule.getTime() + " ");
                        if (rule.getAction())
                            System.out.println("on");
                        else
                            System.out.println("off");
                    }
                }
            } else
                System.out.println("NO SUCH COMMAND!");
        }
    }
}