package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Player {
    private final String division;
    private final String club;
    private final String name;
    private final int age;
    private final String positionString;
    private List<Position> positions = new ArrayList<>(); // Link til Position
    private final String nationality;
    private final String secondNationality;
    private final int height;
    private final String personality;
    private final String recurringInjury;
    private final String euNational;
    private final String homeGrownStatus;
    private final String preferredFoot;
    private final String wage;
    private final String contractExpiryDate;
    private final String transferStatus;
    private final String transferValue;
    private final String wpNeeded;
    private final String wpChance;

    private PP2Universal pp2Universal;
    private PP3CustomStats pp3CustomStats;
    private PP4Shots pp4Shots;
    private PP5Passes pp5Passes;
    private PP6Crosses pp6Crosses;
    private PP7AerialChallenges pp7AerialChallenges;
    private PP8Movement pp8Movement;
    private PP9TacklesAndInterceptions pp9TacklesAndInterceptions;


    public Player(String division, String club, String name, int age, String positionString, String nationality,
                  String secondNationality, int height, String personality, String recurringInjury,
                  String euNational, String homeGrownStatus, String preferredFoot, String wage,
                  String contractExpiryDate, String transferStatus, String transferValue, String wpNeeded,
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
        this.transferStatus = transferStatus;
        this.transferValue = transferValue;
        this.wpNeeded = wpNeeded;
        this.wpChance = wpChance;
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

    public String getContractExpiryDate() {
        return contractExpiryDate;
    }

    public String getTransferStatus() {
        return transferStatus;
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

    public PP2Universal getPp2Universal() {
        return pp2Universal;
    }

    public PP3CustomStats getPp3CustomStats() {
        return pp3CustomStats;
    }

    public PP4Shots getPp4Shots() {
        return pp4Shots;
    }

    public PP5Passes getPp5Passes() {
        return pp5Passes;
    }

    public PP6Crosses getPp6Crosses() {
        return pp6Crosses;
    }

    public PP7AerialChallenges getPp7AerialChallenges() {
        return pp7AerialChallenges;
    }

    public PP8Movement getPp8Movement() {
        return pp8Movement;
    }

    public PP9TacklesAndInterceptions getPp9TacklesAndInterceptions() {
        return pp9TacklesAndInterceptions;
    }

    public void addPosition(Position position) {
        positions.add(position);
    }

    public void setPp2Universal(PP2Universal pp2Universal) {
        this.pp2Universal = pp2Universal;
    }

    public void setPp3CustomStats(PP3CustomStats pp3CustomStats) {
        this.pp3CustomStats = pp3CustomStats;
    }

    public void setPp4Shots(PP4Shots pp4Shots) {
        this.pp4Shots = pp4Shots;
    }

    public void setPp5Passes(PP5Passes pp5Passes) {
        this.pp5Passes = pp5Passes;
    }

    public void setPp6Crosses(PP6Crosses pp6Crosses) {
        this.pp6Crosses = pp6Crosses;
    }

    public void setPp7AerialChallenges(PP7AerialChallenges pp7AerialChallenges) {
        this.pp7AerialChallenges = pp7AerialChallenges;
    }

    public void setPp8Movement(PP8Movement pp8Movement) {
        this.pp8Movement = pp8Movement;
    }

    public void setPp9TacklesAndInterceptions(PP9TacklesAndInterceptions pp9TacklesAndInterceptions) {
        this.pp9TacklesAndInterceptions = pp9TacklesAndInterceptions;
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
        return null;
    }
}
