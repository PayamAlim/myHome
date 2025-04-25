package home.device;

public class Thermostat extends Device{
    private int temperature;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        if (temperature > 30 || temperature < 10)
            throw new IllegalArgumentException("Invalid temperature");

        this.temperature = temperature;
    }

    public Thermostat(String name) {
        super(name);
        temperature = 20;
    }
}