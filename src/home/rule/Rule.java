package home.rule;

import home.device.Device;

import java.util.ArrayList;

public class Rule {
    public static ArrayList<Rule> allRules = new ArrayList<>();

    private Device deviceRef;

    private String time;

    private boolean action;

    public Device getDeviceRef() {
        return deviceRef;
    }

    public String getTime() {
        return time;
    }

    public boolean getAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Rule(Device deviceRef, String time, boolean action) {
        this.action = action;
        this.time = time;
        this.deviceRef = deviceRef;
    }
}
