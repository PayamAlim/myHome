package home.device;

import java.util.ArrayList;

public abstract class Device {
    public static ArrayList<Device> allDevices = new ArrayList<>();

    protected String name;

    protected enum Status {
        On,
        Off;
    }
    protected Status status;

    public Device(String name) {
        status = Status.Off;

        for (Device device: allDevices)
            if (device.name.equals(name))
                throw new IllegalArgumentException("duplicate device name");

        this.name = name;
    }
}
