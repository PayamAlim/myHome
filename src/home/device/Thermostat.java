package home.device;

public class Thermostat extends Device{
    private int temperature;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        if (temperature > 30 || temperature < 10)
            throw new IllegalArgumentException("Invalid value");

        this.temperature = temperature;
    }

    public Thermostat(String name, String protocol) {
        super(name, protocol);
        temperature = 20;
        fillProperties();
    }

    private void fillProperties() {
        properties.add("status");
        properties.add("temperature");
    }
}