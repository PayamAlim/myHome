package home.device;

import java.util.ArrayList;

public class Device {
    public static ArrayList<Device> allDevices = new ArrayList<>();
    public static ArrayList<String> allProtocols = new ArrayList<>();

    public ArrayList<String> properties = new ArrayList<>();

    protected String name;

    protected String protocol;

    public enum Status {
        On,
        Off;
    }

    protected Status status;

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getProtocol() {
        return protocol;
    }

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
