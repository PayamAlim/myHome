package home.device;

public class Light extends Device{
    private int brightness;

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        if (brightness > 100 || brightness < 0)
            throw new IllegalArgumentException("Invalid brightness");

        this.brightness = brightness;
    }

    public Light(String name, String protocol) {
        super(name, protocol);
        brightness = 50;
    }
}
