package model;

public class PP5Passes {
    private int assists;
    private double assistsPer90;
    private double xA;
    private double xAPer90;
    private double prPassesPer90;
    private double passesCompletedPer90;
    private int passCompletionRatio;
    private double openPlayKeyPassesPer90;
    private double keyPassesPer90;
    private double chancesCreatedPer90;

    public PP5Passes(int assists, double assistsPer90, double xA, double xAPer90, double prPassesPer90,
                     double passesCompletedPer90, int passCompletionRatio, double openPlayKeyPassesPer90,
                     double keyPassesPer90, double chancesCreatedPer90) {
        this.assists = assists;
        this.assistsPer90 = assistsPer90;
        this.xA = xA;
        this.xAPer90 = xAPer90;
        this.prPassesPer90 = prPassesPer90;
        this.passesCompletedPer90 = passesCompletedPer90;
        this.passCompletionRatio = passCompletionRatio;
        this.openPlayKeyPassesPer90 = openPlayKeyPassesPer90;
        this.keyPassesPer90 = keyPassesPer90;
        this.chancesCreatedPer90 = chancesCreatedPer90;
    }

    public int getAssists() {
        return assists;
    }

    public double getAssistsPer90() {
        return assistsPer90;
    }

    public double getxA() {
        return xA;
    }

    public double getxAPer90() {
        return xAPer90;
    }

    public double getPrPassesPer90() {
        return prPassesPer90;
    }

    public double getPassesCompletedPer90() {
        return passesCompletedPer90;
    }

    public int getPassCompletionRatio() {
        return passCompletionRatio;
    }

    public double getOpenPlayKeyPassesPer90() {
        return openPlayKeyPassesPer90;
    }

    public double getKeyPassesPer90() {
        return keyPassesPer90;
    }

    public double getChancesCreatedPer90() {
        return chancesCreatedPer90;
    }

    @Override
    public String toString() {
        return "PP5Passes{" +
                "assists=" + assists +
                ", assistsPer90=" + assistsPer90 +
                ", xA=" + xA +
                ", xAPer90=" + xAPer90 +
                ", prPassesPer90=" + prPassesPer90 +
                ", passesCompletedPer90=" + passesCompletedPer90 +
                ", passCompletionRatio=" + passCompletionRatio +
                ", openPlayKeyPassesPer90=" + openPlayKeyPassesPer90 +
                ", keyPassesPer90=" + keyPassesPer90 +
                ", chancesCreatedPer90=" + chancesCreatedPer90 +
                '}';
    }
}
