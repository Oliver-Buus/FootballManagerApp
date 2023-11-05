package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class Player {

    // PlayerInfo
    private final String division;
    private final String club;
    private final String name;
    private final int age;
    private final String positionString;
    private List<Position> positions = new ArrayList<>(); // Link til Position
    private final String nationality;
    private String secondNationality;
    private final int height;
    private final String personality;
    private final String recurringInjury;
    private String euNational;
    private String homeGrownStatus;
    private final String preferredFoot;
    private final String wage;
    private final LocalDate contractExpiryDate;
    private final String transferValue;
    private String wpNeeded;
    private String wpChance;

    // Player Performance links
    private PP2Universal pp2Universal;
    private PP3CustomStats pp3CustomStats;
    private PP4Shots pp4Shots;
    private PP5Passes pp5Passes;
    private PP6Crosses pp6Crosses;
    private PP7AerialChallenges pp7AerialChallenges;
    private PP8Movement pp8Movement;
    private PP9TacklesAndInterceptions pp9TacklesAndInterceptions;
    private PP10Saves pp10Saves;



    public Player(String division, String club, String name, int age, String positionString, String nationality,
                  String secondNationality, int height, String personality, String recurringInjury,
                  String euNational, String homeGrownStatus, String preferredFoot, String wage,
                  LocalDate contractExpiryDate, String transferValue, String wpNeeded,
                  String wpChance) {
        this.division = division;
        this.club = club;
        this.name = name;
        this.age = age;
        this.positionString = positionString;
        this.nationality = nationality;
        this.secondNationality = secondNationality;
        this.height = height;
        this.personality = personality;
        this.recurringInjury = recurringInjury;
        this.euNational = euNational;
        this.homeGrownStatus = homeGrownStatus;
        this.preferredFoot = preferredFoot;
        this.wage = wage;
        this.contractExpiryDate = contractExpiryDate;
        this.transferValue = transferValue;
        this.wpNeeded = wpNeeded;
        this.wpChance = wpChance;
    }

    public Player(String division, String club, String name, int age, String positionString, String nationality,
                  int height, String personality, String recurringInjury, String preferredFoot, String wage,
                  LocalDate contractExpiryDate, String transferValue) {
        this.division = division;
        this.club = club;
        this.name = name;
        this.age = age;
        this.positionString = positionString;
        this.nationality = nationality;
        this.height = height;
        this.personality = personality;
        this.recurringInjury = recurringInjury;
        this.preferredFoot = preferredFoot;
        this.wage = wage;
        this.contractExpiryDate = contractExpiryDate;
        this.transferValue = transferValue;
    }

    public String getDivision() {
        return division;
    }

    public String getClub() {
        return club;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPositionString() {
        return positionString;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public String getNationality() {
        return nationality;
    }

    public String getSecondNationality() {
        return secondNationality;
    }

    public int getHeight() {
        return height;
    }

    public String getPersonality() {
        return personality;
    }

    public String getRecurringInjury() {
        return recurringInjury;
    }

    public String getEuNational() {
        return euNational;
    }

    public String getHomeGrownStatus() {
        return homeGrownStatus;
    }

    public String getPreferredFoot() {
        return preferredFoot;
    }

    public String getWage() {
        return wage;
    }

    public LocalDate getContractExpiryDate() {
        return contractExpiryDate;
    }

    public String getTransferValue() {
        return transferValue;
    }

    public String getWpNeeded() {
        return wpNeeded;
    }

    public String getWpChance() {
        return wpChance;
    }

    //____PP2Universal__________________________________________________________________________________________________
    public void setPp2Universal(PP2Universal pp2Universal) {
        this.pp2Universal = pp2Universal;
    }

    public String getApps() {
        return pp2Universal.getApps();
    }
    public int getMins() {
        return pp2Universal.getMins();
    }
    public double getMinsPerGame() {
        return pp2Universal.getMinsPerGame();
    }
    public int getPlayerOfTheMatch() {
        return pp2Universal.getPlayerOfTheMatch();
    }
    public int getGoalLeadingMistakes() {
        return pp2Universal.getGoalLeadingMistakes();
    }
    public double getAvgRating() {
        return pp2Universal.getAvgRating();
    }
    //____PP2Universal__________________________________________________________________________________________________

    //____PP3CustomStats________________________________________________________________________________________________
    public void setPp3CustomStats(PP3CustomStats pp3CustomStats) {
        this.pp3CustomStats = pp3CustomStats;
    }

    public double getNpxGOP() {
        return pp3CustomStats.getNpxGOP();
    }
    public double getOffsidesPer90() {
        return pp3CustomStats.getOffsidesPer90();
    }
    //____PP3CustomStats________________________________________________________________________________________________


    //____PP4Shots______________________________________________________________________________________________________
    public void setPp4Shots(PP4Shots pp4Shots) {
        this.pp4Shots = pp4Shots;
    }

    public int getGoals() {
        return pp4Shots.getGoals();
    }
    public double getNpxG() {
        return pp4Shots.getNpxG();
    }
    public double getGlsPer90() {
        return pp4Shots.getGlsPer90();
    }
    public double getNpxGPer90() {
        return pp4Shots.getNpxGPer90();
    }
    public double getMinsPerGoal() {
        return pp4Shots.getMinsPerGoal();
    }
    public double getShotsPer90() { return pp4Shots.getShotsPer90();}
    public int getShotsOnTargetRatio() {
        return pp4Shots.getShotsOnTargetRatio();
    }
    public double getShotsOnTargetPer90() {
        return pp4Shots.getShotsOnTargetPer90();
    }
    public double getShotsOutsideBoxPer90() {
        return pp4Shots.getShotsOutsideBoxPer90();
    }
    public int getGoalsOutsideBox() {
        return pp4Shots.getGoalsOutsideBox();
    }
    public double getXGPerShot() {
        return pp4Shots.getxGPerShot();
    }
    public int getConversionRatio() {
        return pp4Shots.getConversionRatio();
    }
    //____PP4Shots______________________________________________________________________________________________________

    //____PP5Passes_____________________________________________________________________________________________________
    public void setPp5Passes(PP5Passes pp5Passes) {
        this.pp5Passes = pp5Passes;
    }

    public int getAssists() {
        return pp5Passes.getAssists();
    }
    public double getAssistsPer90() {
        return pp5Passes.getAssistsPer90();
    }
    public double getXA() {
        return pp5Passes.getxA();
    }
    public double getXAPer90() {
        return pp5Passes.getxAPer90();
    }
    public double getPrPassesPer90() {
        return pp5Passes.getPrPassesPer90();
    }
    public double getPassesCompletedPer90() {
        return pp5Passes.getPassesCompletedPer90();
    }
    public int getPassCompletionRatio() {
        return pp5Passes.getPassCompletionRatio();
    }
    public double getOpenPlayKeyPassesPer90() {
        return pp5Passes.getOpenPlayKeyPassesPer90();
    }
    public double getKeyPassesPer90() {
        return pp5Passes.getKeyPassesPer90();
    }
    public double getChancesCreatedPer90() {
        return pp5Passes.getChancesCreatedPer90();
    }
    //____PP5Passes_____________________________________________________________________________________________________

    //____PP6Crosses____________________________________________________________________________________________________
    public void setPp6Crosses(PP6Crosses pp6Crosses) {
        this.pp6Crosses = pp6Crosses;
    }

    public double getOpenPlayCrossesCompletedPer90() {
        return pp6Crosses.getOpenPlayCrossesCompletedPer90();
    }
    public int getOpenPlayCrossesCompletedRatio() {
        return pp6Crosses.getOpenPlayCrossesCompletedRatio();
    }

    //____PP6Crosses____________________________________________________________________________________________________

    //____PP7AerialChallenges___________________________________________________________________________________________
    public void setPp7AerialChallenges(PP7AerialChallenges pp7AerialChallenges) {
        this.pp7AerialChallenges = pp7AerialChallenges;
    }

    public double getKeyHeadersPer90() {
        return pp7AerialChallenges.getKeyHeadersPer90();
    }
    public int getHeadersWonRatio() {
        return pp7AerialChallenges.getHeadersWonRatio();
    }
    public double getHeadersWonPer90() {
        return pp7AerialChallenges.getHeadersWonPer90();
    }
    //____PP7AerialChallenges___________________________________________________________________________________________

    //____PP8Movement___________________________________________________________________________________________________
    public void setPp8Movement(PP8Movement pp8Movement) {
        this.pp8Movement = pp8Movement;
    }

    public int getFoulsAgainst() {
        return pp8Movement.getFoulsAgainst();
    }
    public double getPressuresCompletedPer90() {
        return pp8Movement.getPressuresCompletedPer90();
    }
    public double getPressuresAttemptedPer90() {
        return pp8Movement.getPressuresAttemptedPer90();
    }
    public int getPressuresCompletedRatio() {
        return pp8Movement.getPressuresCompletedRatio();
    }
    public double getSprintsPer90() {
        return pp8Movement.getSprintsPer90();
    }
    public double getDribblesPer90() {
        return pp8Movement.getDribblesPer90();
    }
    public double getDistanceCoveredPer90() {
        return pp8Movement.getDistanceCoveredPer90();
    }
    public double getPossessionLostPer90() {
        return pp8Movement.getPossessionLostPer90();
    }
    //____PP8Movement___________________________________________________________________________________________________

    //____PP9TacklesAndInterceptions____________________________________________________________________________________
    public void setPp9TacklesAndInterceptions(PP9TacklesAndInterceptions pp9TacklesAndInterceptions) {
        this.pp9TacklesAndInterceptions = pp9TacklesAndInterceptions;
    }

    public int getFoulsCommitted() {
        return pp9TacklesAndInterceptions.getFoulsCommitted();
    }
    public double getTacklesWonPer90() {
        return pp9TacklesAndInterceptions.getTacklesWonPer90();
    }
    public int getTacklesWonRatio() {
        return pp9TacklesAndInterceptions.getTacklesWonRatio();
    }
    public double getShotsBlockedPer90() {
        return pp9TacklesAndInterceptions.getShotsBlockedPer90();
    }
    public double getPossessionWonPer90() {
        return pp9TacklesAndInterceptions.getPossessionWonPer90();
    }
    public double getKeyTacklesPer90() {
        return pp9TacklesAndInterceptions.getKeyTacklesPer90();
    }
    public double getInterceptionsPer90() {
        return pp9TacklesAndInterceptions.getInterceptionsPer90();
    }
    public double getClearancesPer90() {
        return pp9TacklesAndInterceptions.getClearancesPer90();
    }
    public double getBlocksPer90() {
        return pp9TacklesAndInterceptions.getBlocksPer90();
    }
    //____PP9TacklesAndInterceptions____________________________________________________________________________________

    //____PP10Saves_____________________________________________________________________________________________________
    public void setPp10Saves(PP10Saves pp10Saves) {
        this.pp10Saves = pp10Saves;
    }

    public double getSavesPer90() {
        return pp10Saves.getSavesPer90();
    }
    public double getConcededPer90() {
        return pp10Saves.getConcededPer90();
    }
    public double getCleanSheetsPer90() {
        return pp10Saves.getCleanSheetsPer90();
    }
    public int getSaveRatio() {
        return pp10Saves.getSaveRatio();
    }
    public int getXsaveRatio() {
        return pp10Saves.getXsaveRatio();
    }
    public double getXGoalsPreventedPer90() {
        return pp10Saves.getxGoalsPreventedPer90();
    }
    //____PP10Saves_____________________________________________________________________________________________________

    public void addPosition(Position position) {
        positions.add(position);
    }

    public void addPositionsToPlayer() {
        File[] files = new File[13];
        files[0] = new File("FM MoneyBall/src/resources/DR.txt");
        files[1] = new File("FM MoneyBall/src/resources/DL.txt");
        files[2] = new File("FM MoneyBall/src/resources/DC.txt");
        files[3] = new File("FM MoneyBall/src/resources/WBR.txt");
        files[4] = new File("FM MoneyBall/src/resources/WBL.txt");
        files[5] = new File("FM MoneyBall/src/resources/DM.txt");
        files[6] = new File("FM MoneyBall/src/resources/MR.txt");
        files[7] = new File("FM MoneyBall/src/resources/ML.txt");
        files[8] = new File("FM MoneyBall/src/resources/MC.txt");
        files[9] = new File("FM MoneyBall/src/resources/AMR.txt");
        files[10] = new File("FM MoneyBall/src/resources/AML.txt");
        files[11] = new File("FM MoneyBall/src/resources/AMC.txt");
        files[12] = new File("FM MoneyBall/src/resources/STC.txt");

        Position[] positions = {Position.DR, Position.DL, Position.DC, Position.WBR, Position.WBL, Position.DM,
        Position.MR, Position.ML, Position.MC, Position.AMR, Position.AML, Position.AMC, Position.STC};

        for (int i = 0; i < files.length; i++) {
            try {
                Scanner reader = new Scanner(files[i]);

                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    if (line.equals(this.getPositionString()))
                        this.addPosition(positions[i]);
                    else if (this.getPositionString().contains("GK"))
                        this.addPosition(Position.GK);
                }
                reader.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "division='" + division + '\'' +
                ", club='" + club + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", positionString='" + positionString + '\'' +
                ", positions=" + positions +
                ", nationality='" + nationality + '\'' +
                ", secondNationality='" + secondNationality + '\'' +
                ", height=" + height +
                ", personality='" + personality + '\'' +
                ", recurringInjury='" + recurringInjury + '\'' +
                ", euNational='" + euNational + '\'' +
                ", homeGrownStatus='" + homeGrownStatus + '\'' +
                ", preferredFoot='" + preferredFoot + '\'' +
                ", wage='" + wage + '\'' +
                ", contractExpiryDate=" + contractExpiryDate +
                ", transferValue='" + transferValue + '\'' +
                ", wpNeeded='" + wpNeeded + '\'' +
                ", wpChance='" + wpChance + '\'' +
                ", pp2Universal=" + pp2Universal.toString() +
                ", pp3CustomStats=" + pp3CustomStats.toString() +
                ", pp4Shots=" + pp4Shots.toString() +
                ", pp5Passes=" + pp5Passes.toString() +
                ", pp6Crosses=" + pp6Crosses.toString() +
                ", pp7AerialChallenges=" + pp7AerialChallenges.toString() +
                ", pp8Movement=" + pp8Movement.toString() +
                ", pp9TacklesAndInterceptions=" + pp9TacklesAndInterceptions.toString() +
                ", pp10Saves=" + pp10Saves.toString() +
                '}';
    }
}
