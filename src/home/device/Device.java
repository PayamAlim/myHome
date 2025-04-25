package home.device;

import java.util.ArrayList;

public abstract class Device {
    public static ArrayList<Device> allDevices = new ArrayList<>();
    public static ArrayList<String> allProtocols = new ArrayList<>();

    protected String name, protocol;

    protected enum Status {
        On,
        Off;
    }
    protected Status status;

    public Device(String name, String protocol) {
        status = Status.Off;

        validateDeviceName(name);
        validateDeviceProtocol(protocol);

        this.name = name;
        this.protocol = protocol;
    }

    private void validateDeviceName(String name) {
        for (Device device: allDevices)
            if (device.name.equalsIgnoreCase(name))
                throw new IllegalArgumentException("duplicate device name");
    }

    private void validateDeviceProtocol(String protocolName) {
        for (String protocol: allProtocols)
            if (protocol.equalsIgnoreCase(protocolName))
                return;
        throw new IllegalArgumentException("no such protocol");
    }
}
