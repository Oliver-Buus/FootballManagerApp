package model;

public class PP3CustomStats {
    private double npxGOP;
    private double offsidesPer90;

    public PP3CustomStats(double npxGOP, double offsidesPer90) {
        this.npxGOP = npxGOP;
        this.offsidesPer90 = offsidesPer90;
    }

    public double getNpxGOP() {
        return npxGOP;
    }

    public double getOffsidesPer90() {
        return offsidesPer90;
    }
}
