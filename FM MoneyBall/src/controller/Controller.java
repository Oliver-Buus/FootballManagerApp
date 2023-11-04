package controller;

import model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import storage.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Controller {


    public static Player addPPsToPlayer(Player player, PP2Universal pp2Universal, PP4Shots pp4Shots, PP5Passes pp5Passes) {
        player.setPp2Universal(pp2Universal);
        player.setPp4Shots(pp4Shots);
        player.setPp5Passes(pp5Passes);


        return player;
    }

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

    public static PP2Universal createPP2Universal(String apps, int mins, double minsPerGame, double avgRating) {

        PP2Universal pp2Universal = new PP2Universal(apps, mins, minsPerGame, avgRating);
        Storage.storePP2Universal(pp2Universal);

        return pp2Universal;
    }

    public static PP4Shots createPP4Shots(int goals, double npxG, double glsPer90, double npxGPer90,
                                          double minsPerGoal, double shotsPer90, int shotsOnTargetRatio,
                                          double shotsOnTargetPer90, double shotsOutsideBoxPer90,
                                          int goalsOutsideBox, double xGPerShot, int conversionRatio) {
        PP4Shots pp4Shots = new PP4Shots(goals, npxG, glsPer90, npxGPer90, minsPerGoal, shotsPer90,
                shotsOnTargetRatio, shotsOnTargetPer90, shotsOutsideBoxPer90, goalsOutsideBox, xGPerShot,
                conversionRatio);

        return pp4Shots;
    }

    public static PP5Passes createPP5Passes(int assists, double assistsPer90, double xA, double xAPer90,
                                            double prPassesPer90, double passesCompletedPer90,
                                            int passCompletionRatio, double openPlayKeyPassesPer90,
                                            double keyPassesPer90, double chancesCreatedPer90) {
        PP5Passes pp5Passes = new PP5Passes(assists, assistsPer90, xA, xAPer90, prPassesPer90, passesCompletedPer90,
                passCompletionRatio, openPlayKeyPassesPer90, keyPassesPer90, chancesCreatedPer90);

        return pp5Passes;
    }

    public static void createPlayerFromHTML(String filePath) {
        File file = new File("C:\\Users\\failo\\Documents\\Sports Interactive\\" +
                "Football Manager 2024\\AllColumns.html");
        //file = new File(filePath);

        try {
            Document document = Jsoup.parse(file);

//            int count = 0;
//            for (Element heading : document.select("table th")) {
//                count++;
//                System.out.println(count + " " + heading.text());
//            }
            for (Element row : document.select("table tr")) {
                if (row.select("td").text().isEmpty()) continue;


                Player player = createPlayerFromRowInHTML(row);
                PP2Universal pp2Universal = createPP2UniversalFromRowInHTML(row);
                PP4Shots pp4Shots = createPP4ShotsFromRowInHTML(row);
                PP5Passes pp5Passes = createPP5PassesFromRowInHTML(row);

                addPPsToPlayer(player, pp2Universal, pp4Shots, pp5Passes);





            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Player createPlayerFromRowInHTML(Element row) {
        String division = row.select("td:nth-child(1)").text();
        String club = row.select("td:nth-child(2)").text();
        String name = row.select("td:nth-child(3)").text();
        int age = Integer.parseInt(row.select("td:nth-child(4)").text());
        String positionString = row.select("td:nth-child(5)").text();
        String nationality = row.select("td:nth-child(6)").text();
        String secondNationality = row.select("td:nth-child(7)").text();
        int height = Integer.parseInt(row.select("td:nth-child(8)").text().replaceAll("\\s.*", ""));
        String personality = row.select("td:nth-child(9)").text();
        String recurringInjury = row.select("td:nth-child(10)").text();
        String euNational = row.select("td:nth-child(11)").text();
        String homeGrownStatus = row.select("td:nth-child(12)").text();
        String prefFoot = row.select("td:nth-child(13)").text();

        String wage = row.select("td:nth-child(14)").text();
        if (wage.equals("N/A") || wage.equals("-")) wage = "0";

        LocalDate expires = LocalDateFormatter(row.select("td:nth-child(15)").text());
        String transferValue = row.select("td:nth-child(17)").text();
        String wpNeeded = row.select("td:nth-child(18)").text();
        String wpChance = row.select("td:nth-child(19)").text();

        return createPlayer(division, club, name, age, positionString, nationality, secondNationality,
                height, personality, recurringInjury, euNational, homeGrownStatus, prefFoot, wage,
                expires, transferValue, wpNeeded, wpChance);
    }


    public static PP2Universal createPP2UniversalFromRowInHTML(Element row) {
        String apps = row.select("td:nth-child(20)").text();
        int mins = parseInt(row.select("td:nth-child(21)").text().replaceAll(",", ""));
        double minsPerGame = parseDouble(row.select("td:nth-child(22)").text());
        double avgRating = round(parseDouble(row.select("td:nth-child(25)").text()), 2);

        return createPP2Universal(apps, mins, minsPerGame, avgRating);
    }
    public static PP3CustomStats createPP3CustomStatsFromRowInHTML(Element row, Player player) {
        int penScored = parseInt(row.select("td:nth-child(35)").text());
        int goals = player.getGoals();
        double npxG = player.getNpxG();

        double npxGOP = round((goals - penScored) - npxG, 2);


        return null;
    }

    public static PP4Shots createPP4ShotsFromRowInHTML(Element row) {
        int goals = parseInt(row.select("td:nth-child(26)").text());
        double npxG = parseDouble(row.select("td:nth-child(28)").text());
        double glsPer90 = parseDouble(row.select("td:nth-child(29)").text());
        double npxGPer90 = parseDouble(row.select("td:nth-child(31)").text());
        double minsPerGoal = round(parseDouble(row.select("td:nth-child(36)").text()),0);
        double shotsPer90 = parseDouble(row.select("td:nth-child(37)").text());
        int shotsOnTargetRatio = parseInt(row.select("td:nth-child(38)").text());
        double shotsOnTargetPer90 = parseDouble(row.select("td:nth-child(39)").text());
        double shotsOutsideBoxPer90 = parseDouble(row.select("td:nth-child(41)").text());
        int goalsOutsideBox = parseInt(row.select("td:nth-child(43)").text());
        double xGPerShot = parseDouble(row.select("td:nth-child(45)").text());
        int conversionRatio = parseInt(row.select("td:nth-child(46)").text());

        return createPP4Shots(goals, npxG, glsPer90, npxGPer90, minsPerGoal, shotsPer90,
                shotsOnTargetRatio, shotsOnTargetPer90, shotsOutsideBoxPer90, goalsOutsideBox,
                xGPerShot, conversionRatio);
    }

    public static PP5Passes createPP5PassesFromRowInHTML(Element row) {
        int assists = parseInt(row.select("td:nth-child(49)").text());
        double assistsPer90 = parseDouble(row.select("td:nth-child(50)").text());
        double xA = parseDouble(row.select("td:nth-child(47)").text());
        double xAPer90 = parseDouble(row.select("td:nth-child(48)").text());
        double prPassesPer90 = parseDouble(row.select("td:nth-child(51)").text());
        double passesCompletedPer90 = parseDouble(row.select("td:nth-child(53)").text());
        int passCompletionRatio = parseInt(row.select("td:nth-child(57)").text());
        double openPlayKeyPassesPer90 = parseDouble(row.select("td:nth-child(58)").text());
        double keyPassesPer90 = parseDouble(row.select("td:nth-child(60)").text());
        double chancesCreatedPer90 = parseDouble(row.select("td:nth-child(63)").text());


        return createPP5Passes(assists, assistsPer90, xA, xAPer90, prPassesPer90, passesCompletedPer90,
                passCompletionRatio, openPlayKeyPassesPer90, keyPassesPer90, chancesCreatedPer90);
    }

    public static LocalDate LocalDateFormatter(String date) {
        if (date.equals("-")) return null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(date, dateTimeFormatter);
    }

    public static int parseInt(String string) {
        try {
            if (string.contains("%")) string = string.replace("%", "");
            return Integer.parseInt(string);
        } catch (Exception e) {
            return 0;
        }

    }

    public static double parseDouble(String string) {
        try {
            return Double.parseDouble(string);
        } catch (Exception e) {
            return 0;
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
