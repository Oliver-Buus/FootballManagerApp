package model;

public class PP2Universal {
    private final String apps;
    private final int mins;
    private final double minsPerGame;
    private final int playerOfTheMatch;
    private final int goalLeadingMistakes;
    private final double avgRating;

    public PP2Universal(String apps, int mins, double minsPerGame, int playerOfTheMatch, int goalLeadingMistakes, double avgRating) {
        this.apps = apps;
        this.mins = mins;
        this.minsPerGame = minsPerGame;
        this.playerOfTheMatch = playerOfTheMatch;
        this.goalLeadingMistakes = goalLeadingMistakes;
        this.avgRating = avgRating;
    }

    public String getApps() {
        return apps;
    }

    public int getMins() {
        return mins;
    }

    public double getMinsPerGame() {
        return minsPerGame;
    }

    public int getPlayerOfTheMatch() {
        return playerOfTheMatch;
    }

    public int getGoalLeadingMistakes() {
        return goalLeadingMistakes;
    }

    public double getAvgRating() {
        return avgRating;
    }
}
