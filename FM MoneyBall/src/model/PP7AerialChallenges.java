package model;

public class PP7AerialChallenges {
    private double keyHeadersPer90;
    private int headersWonRatio;
    private double headersWonPer90;

    public PP7AerialChallenges(double keyHeadersPer90, int headersWonRatio, double headersWonPer90) {
        this.keyHeadersPer90 = keyHeadersPer90;
        this.headersWonRatio = headersWonRatio;
        this.headersWonPer90 = headersWonPer90;
    }

    public double getKeyHeadersPer90() {
        return keyHeadersPer90;
    }

    public int getHeadersWonRatio() {
        return headersWonRatio;
    }

    public double getHeadersWonPer90() {
        return headersWonPer90;
    }

    @Override
    public String toString() {
        return "PP7AerialChallenges{" +
                "keyHeadersPer90=" + keyHeadersPer90 +
                ", headersWonRatio=" + headersWonRatio +
                ", headersWonPer90=" + headersWonPer90 +
                '}';
    }
}
