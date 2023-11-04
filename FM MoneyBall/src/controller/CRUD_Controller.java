package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class CRUD_Controller {

    // Player___________________________________________________________________________________________________________
    public static Player createPlayer(String division, String club, String name, int age, String positionString,
                                      String nationality, String secondNationality, int height, String personality,
                                      String recurringInjury, String euNational, String homeGrownStatus,
                                      String preferredFoot, String wage, LocalDate contractExpiryDate,
                                      String transferValue, String wpNeeded,
                                      String wpChance) {
        Player player = new Player(division, club, name, age, positionString, nationality,
                secondNationality, height, personality, recurringInjury, euNational,
                homeGrownStatus, preferredFoot, wage, contractExpiryDate,
                transferValue, wpNeeded, wpChance);
        player.addPositionsToPlayer();
        Storage.storePlayer(player);
        return player;
    }

    public static ArrayList<Player> getAllPlayers() {
        return Storage.getPlayers();
    }

    public static boolean removeAllPlayers() {
        return Storage.removePlayers();
    }
    //__________________________________________________________________________________________________________________

    //_PP2Universal_____________________________________________________________________________________________________
    public static PP2Universal createPP2Universal(String apps, int mins, double minsPerGame, double avgRating) {
        PP2Universal pp2Universal = new PP2Universal(apps, mins, minsPerGame, avgRating);

        return pp2Universal;
    }

    //__________________________________________________________________________________________________________________

    //_PP3CustomStats___________________________________________________________________________________________________
    public static PP3CustomStats createPP3CustomStats() {
        PP3CustomStats pp3CustomStats = new PP3CustomStats();

        return null;
    }
    //__________________________________________________________________________________________________________________

    //_PP4Shots_________________________________________________________________________________________________________
    public static PP4Shots createPP4Shots(int goals, double npxG, double glsPer90, double npxGPer90,
                                          double minsPerGoal, double shotsPer90, int shotsOnTargetRatio,
                                          double shotsOnTargetPer90, double shotsOutsideBoxPer90,
                                          int goalsOutsideBox, double xGPerShot, int conversionRatio) {
        PP4Shots pp4Shots = new PP4Shots(goals, npxG, glsPer90, npxGPer90, minsPerGoal, shotsPer90,
                shotsOnTargetRatio, shotsOnTargetPer90, shotsOutsideBoxPer90, goalsOutsideBox, xGPerShot,
                conversionRatio);

        return pp4Shots;
    }
    //__________________________________________________________________________________________________________________

    //_PP5Passes________________________________________________________________________________________________________
    public static PP5Passes createPP5Passes(int assists, double assistsPer90, double xA, double xAPer90,
                                            double prPassesPer90, double passesCompletedPer90,
                                            int passCompletionRatio, double openPlayKeyPassesPer90,
                                            double keyPassesPer90, double chancesCreatedPer90) {
        PP5Passes pp5Passes = new PP5Passes(assists, assistsPer90, xA, xAPer90, prPassesPer90, passesCompletedPer90,
                passCompletionRatio, openPlayKeyPassesPer90, keyPassesPer90, chancesCreatedPer90);

        return pp5Passes;
    }
    //__________________________________________________________________________________________________________________

    //_PP6Crosses_______________________________________________________________________________________________________
    public static PP6Crosses createPP6Crosses(double openPlayCrossesCompletedPer90, int openPlayCrossesCompletedRatio) {
        PP6Crosses pp6Crosses = new PP6Crosses(openPlayCrossesCompletedPer90, openPlayCrossesCompletedRatio);
        return pp6Crosses;
    }
    //__________________________________________________________________________________________________________________

    //_PP7AerialChallenges______________________________________________________________________________________________
    public static PP7AerialChallenges createPP7AerialChallenges(double keyHeadersPer90, int headersWonRatio,
                                                                double headersWonPer90) {
        PP7AerialChallenges pp7AerialChallenges = new PP7AerialChallenges(keyHeadersPer90, headersWonRatio,
                headersWonPer90);
        return pp7AerialChallenges;
    }
    //__________________________________________________________________________________________________________________

    //_PP8Movement______________________________________________________________________________________________________
    public static PP8Movement createPP8Movement(int foulsAgainst, double pressuresCompletedPer90,
                                                double pressuresAttemptedPer90, int pressuresCompletedRatio,
                                                double sprintsPer90, double dribblesPer90, double distanceCoveredPer90,
                                                double possessionLostPer90) {
        PP8Movement pp8Movement = new PP8Movement(foulsAgainst, pressuresCompletedPer90, pressuresAttemptedPer90,
                pressuresCompletedRatio, sprintsPer90, dribblesPer90, distanceCoveredPer90, possessionLostPer90);

        return pp8Movement;
    }
    //__________________________________________________________________________________________________________________

    //_PP9TacklesAndInterceptions_______________________________________________________________________________________
    public static PP9TacklesAndInterceptions createPP9TacklesAndInterceptions(int foulsCommitted,
                                                                              double tacklesWonPer90,
                                                                              int tacklesWonRatio,
                                                                              double shotsBlockedPer90,
                                                                              double possessionWonPer90,
                                                                              double keyTacklesPer90,
                                                                              double interceptionsPer90,
                                                                              double clearancesPer90,
                                                                              double blocksPer90) {
        PP9TacklesAndInterceptions pp9TacklesAndInterceptions =
                new PP9TacklesAndInterceptions(foulsCommitted, tacklesWonPer90, tacklesWonRatio, shotsBlockedPer90,
                        possessionWonPer90, keyTacklesPer90, interceptionsPer90, clearancesPer90, blocksPer90);

        return pp9TacklesAndInterceptions;
    }
    //__________________________________________________________________________________________________________________

    //_PP10Saves________________________________________________________________________________________________________
    public static PP10Saves createPP10Saves(double savesPer90, double concededPer90, double cleanSheetsPer90,
                                            int saveRatio, int xsaveRatio, double xGoalsPreventedPer90) {
        PP10Saves pp10Saves = new PP10Saves(savesPer90, concededPer90, cleanSheetsPer90, saveRatio, xsaveRatio,
                xGoalsPreventedPer90);
        return pp10Saves;
    }
    //__________________________________________________________________________________________________________________




}
