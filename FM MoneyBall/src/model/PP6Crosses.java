package model;

public class PP6Crosses {
    private double openPlayCrossesCompletedPer90;
    private int openPlayCrossesCompletedRatio;

    public PP6Crosses(double openPlayCrossesCompletedPer90, int openPlayCrossesCompletedRatio) {
        this.openPlayCrossesCompletedPer90 = openPlayCrossesCompletedPer90;
        this.openPlayCrossesCompletedRatio = openPlayCrossesCompletedRatio;
    }

    public double getOpenPlayCrossesCompletedPer90() {
        return openPlayCrossesCompletedPer90;
    }

    public int getOpenPlayCrossesCompletedRatio() {
        return openPlayCrossesCompletedRatio;
    }

    @Override
    public String toString() {
        return "PP6Crosses{" +
                "openPlayCrossesCompletedPer90=" + openPlayCrossesCompletedPer90 +
                ", openPlayCrossesCompletedRatio=" + openPlayCrossesCompletedRatio +
                '}';
    }
}
