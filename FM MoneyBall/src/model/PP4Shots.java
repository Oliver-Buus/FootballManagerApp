package model;

public class PP4Shots {
    private int goals;
    private double xG;
    private double npxG;
    private double glsPer90;
    private double xGPer90;
    private double npxGPer90;
    private double xGOP;
    private double minsPerGoal;
    private double shotsPer90;
    private int shotsOnTargetRatio;
    private double shotsOnTargetPer90;
    private int shotsOnTarget;
    private double shotsOutsideBoxPer90;
    private int shots;
    private int goalsOutsideBox;
    private int freekickShots;
    private double xGPerShot;
    private int conversionRatio;

    public PP4Shots(int goals, double npxG, double glsPer90, double npxGPer90, double minsPerGoal,
                    double shotsPer90, int shotsOnTargetRatio, double shotsOnTargetPer90, double shotsOutsideBoxPer90,
                    int goalsOutsideBox, double xGPerShot, int conversionRatio) {
        this.goals = goals;
        this.npxG = npxG;
        this.glsPer90 = glsPer90;
        this.npxGPer90 = npxGPer90;
        this.minsPerGoal = minsPerGoal;
        this.shotsPer90 = shotsPer90;
        this.shotsOnTargetRatio = shotsOnTargetRatio;
        this.shotsOnTargetPer90 = shotsOnTargetPer90;
        this.shotsOutsideBoxPer90 = shotsOutsideBoxPer90;
        this.goalsOutsideBox = goalsOutsideBox;
        this.xGPerShot = xGPerShot;
        this.conversionRatio = conversionRatio;
    }

    public int getGoals() {
        return goals;
    }

    public double getxG() {
        return xG;
    }

    public double getNpxG() {
        return npxG;
    }

    public double getGlsPer90() {
        return glsPer90;
    }

    public double getxGPer90() {
        return xGPer90;
    }

    public double getNpxGPer90() {
        return npxGPer90;
    }

    public double getxGOP() {
        return xGOP;
    }

    public double getMinsPerGoal() {
        return minsPerGoal;
    }

    public double getShotsPer90() {
        return shotsPer90;
    }

    public int getShotsOnTargetRatio() {
        return shotsOnTargetRatio;
    }

    public double getShotsOnTargetPer90() {
        return shotsOnTargetPer90;
    }

    public int getShotsOnTarget() {
        return shotsOnTarget;
    }

    public double getShotsOutsideBoxPer90() {
        return shotsOutsideBoxPer90;
    }

    public int getShots() {
        return shots;
    }

    public int getGoalsOutsideBox() {
        return goalsOutsideBox;
    }

    public int getFreekickShots() {
        return freekickShots;
    }

    public double getxGPerShot() {
        return xGPerShot;
    }

    public int getConversionRatio() {
        return conversionRatio;
    }

}
