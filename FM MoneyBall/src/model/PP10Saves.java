package model;

public class PP10Saves {
    private double savesPer90; // ER ÅBENBART EN INT
    private double concededPer90;
    private double cleanSheetsPer90;
    private int saveRatio;
    private int xsaveRatio;
    private double xGoalsPreventedPer90;

    public PP10Saves(double savesPer90, double concededPer90, double cleanSheetsPer90, int saveRatio, int xsaveRatio,
                     double xGoalsPreventedPer90) {
        this.savesPer90 = savesPer90;
        this.concededPer90 = concededPer90;
        this.cleanSheetsPer90 = cleanSheetsPer90;
        this.saveRatio = saveRatio;
        this.xsaveRatio = xsaveRatio;
        this.xGoalsPreventedPer90 = xGoalsPreventedPer90;
    }

    public double getSavesPer90() {
        return savesPer90;
    }

    public double getConcededPer90() {
        return concededPer90;
    }

    public double getCleanSheetsPer90() {
        return cleanSheetsPer90;
    }

    public int getSaveRatio() {
        return saveRatio;
    }

    public int getXsaveRatio() {
        return xsaveRatio;
    }

    public double getxGoalsPreventedPer90() {
        return xGoalsPreventedPer90;
    }

    @Override
    public String toString() {
        return "PP10Saves{" +
                "savesPer90=" + savesPer90 +
                ", concededPer90=" + concededPer90 +
                ", cleanSheetsPer90=" + cleanSheetsPer90 +
                ", saveRatio=" + saveRatio +
                ", xsaveRatio=" + xsaveRatio +
                ", xGoalsPreventedPer90=" + xGoalsPreventedPer90 +
                '}';
    }
}
