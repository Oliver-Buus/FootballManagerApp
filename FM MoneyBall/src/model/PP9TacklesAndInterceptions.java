package model;

public class PP9TacklesAndInterceptions {
    private int foulsCommitted;
    private double tacklesWonPer90;
    private int tacklesWonRatio;
    private double shotsBlockedPer90;
    private double possessionWonPer90;
    private double keyTacklesPer90;
    private double interceptionsPer90;
    private double clearancesPer90;
    private double blocksPer90;

    public PP9TacklesAndInterceptions(int foulsCommitted, double tacklesWonPer90, int tacklesWonRatio,
                                      double shotsBlockedPer90, double possessionWonPer90, double keyTacklesPer90,
                                      double interceptionsPer90, double clearancesPer90, double blocksPer90) {
        this.foulsCommitted = foulsCommitted;
        this.tacklesWonPer90 = tacklesWonPer90;
        this.tacklesWonRatio = tacklesWonRatio;
        this.shotsBlockedPer90 = shotsBlockedPer90;
        this.possessionWonPer90 = possessionWonPer90;
        this.keyTacklesPer90 = keyTacklesPer90;
        this.interceptionsPer90 = interceptionsPer90;
        this.clearancesPer90 = clearancesPer90;
        this.blocksPer90 = blocksPer90;
    }

    public int getFoulsCommitted() {
        return foulsCommitted;
    }

    public double getTacklesWonPer90() {
        return tacklesWonPer90;
    }

    public int getTacklesWonRatio() {
        return tacklesWonRatio;
    }

    public double getShotsBlockedPer90() {
        return shotsBlockedPer90;
    }

    public double getPossessionWonPer90() {
        return possessionWonPer90;
    }

    public double getKeyTacklesPer90() {
        return keyTacklesPer90;
    }

    public double getInterceptionsPer90() {
        return interceptionsPer90;
    }

    public double getClearancesPer90() {
        return clearancesPer90;
    }

    public double getBlocksPer90() {
        return blocksPer90;
    }

    @Override
    public String toString() {
        return "PP9TacklesAndInterceptions{" +
                "foulsCommitted=" + foulsCommitted +
                ", tacklesWonPer90=" + tacklesWonPer90 +
                ", tacklesWonRatio=" + tacklesWonRatio +
                ", shotsBlockedPer90=" + shotsBlockedPer90 +
                ", possessionWonPer90=" + possessionWonPer90 +
                ", keyTacklesPer90=" + keyTacklesPer90 +
                ", interceptionsPer90=" + interceptionsPer90 +
                ", clearancesPer90=" + clearancesPer90 +
                ", blocksPer90=" + blocksPer90 +
                '}';
    }
}
