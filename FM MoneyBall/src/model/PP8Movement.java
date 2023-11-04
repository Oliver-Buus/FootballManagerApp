package model;

public class PP8Movement {
    private int foulsAgainst;
    private double pressuresCompletedPer90;
    private double pressuresAttemptedPer90;
    private int pressuresCompletedRatio;
    private double sprintsPer90;
    private double dribblesPer90;
    private double distanceCoveredPer90;
    private double possessionLostPer90;

    public PP8Movement(int foulsAgainst, double pressuresCompletedPer90, double pressuresAttemptedPer90,
                       int pressuresCompletedRatio, double sprintsPer90, double dribblesPer90,
                       double distanceCoveredPer90, double possessionLostPer90) {
        this.foulsAgainst = foulsAgainst;
        this.pressuresCompletedPer90 = pressuresCompletedPer90;
        this.pressuresAttemptedPer90 = pressuresAttemptedPer90;
        this.pressuresCompletedRatio = pressuresCompletedRatio;
        this.sprintsPer90 = sprintsPer90;
        this.dribblesPer90 = dribblesPer90;
        this.distanceCoveredPer90 = distanceCoveredPer90;
        this.possessionLostPer90 = possessionLostPer90;
    }

    public int getFoulsAgainst() {
        return foulsAgainst;
    }

    public double getPressuresCompletedPer90() {
        return pressuresCompletedPer90;
    }

    public double getPressuresAttemptedPer90() {
        return pressuresAttemptedPer90;
    }

    public int getPressuresCompletedRatio() {
        return pressuresCompletedRatio;
    }

    public double getSprintsPer90() {
        return sprintsPer90;
    }

    public double getDribblesPer90() {
        return dribblesPer90;
    }

    public double getDistanceCoveredPer90() {
        return distanceCoveredPer90;
    }

    public double getPossessionLostPer90() {
        return possessionLostPer90;
    }

    @Override
    public String toString() {
        return "PP8Movement{" +
                "foulsAgainst=" + foulsAgainst +
                ", pressuresCompletedPer90=" + pressuresCompletedPer90 +
                ", pressuresAttemptedPer90=" + pressuresAttemptedPer90 +
                ", sprintsPer90=" + sprintsPer90 +
                ", dribblesPer90=" + dribblesPer90 +
                ", distanceCoveredPer90=" + distanceCoveredPer90 +
                ", possessionLostPer90=" + possessionLostPer90 +
                '}';
    }
}
