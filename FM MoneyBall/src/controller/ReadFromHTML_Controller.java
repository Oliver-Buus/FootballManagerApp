package controller;

import model.*;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReadFromHTML_Controller {

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

        return CRUD_Controller.createPlayer(division, club, name, age, positionString, nationality, secondNationality,
                height, personality, recurringInjury, euNational, homeGrownStatus, prefFoot, wage,
                expires, transferValue, wpNeeded, wpChance);
    }


    public static PP2Universal createPP2UniversalFromRowInHTML(Element row) {
        String apps = row.select("td:nth-child(20)").text();
        int mins = parseInt(row.select("td:nth-child(21)").text().replaceAll(",", ""));
        double minsPerGame = parseDouble(row.select("td:nth-child(22)").text());
        double avgRating = round(parseDouble(row.select("td:nth-child(25)").text()), 2);

        return CRUD_Controller.createPP2Universal(apps, mins, minsPerGame, avgRating);
    }
    public static PP3CustomStats createPP3CustomStatsFromRowInHTML(Element row, Player player) {
        int penScored = parseInt(row.select("td:nth-child(35)").text());
        int goals = player.getGoals();
        double npxG = player.getNpxG();

        double npxGOP = round((goals - penScored) - npxG, 2);

        int offsides = parseInt(row.select("td:nth-child(88)").text());
        double offsidesPer90 = 0.0;
        int mins = player.getMins();
        if (mins > 0 && offsides > 0) offsidesPer90 = offsides / (mins / 90.0);
        offsidesPer90 = round(offsidesPer90, 2);

        return CRUD_Controller.createPP3CustomStats(npxGOP, offsidesPer90);
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

        return CRUD_Controller.createPP4Shots(goals, npxG, glsPer90, npxGPer90, minsPerGoal, shotsPer90,
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


        return CRUD_Controller.createPP5Passes(assists, assistsPer90, xA, xAPer90, prPassesPer90, passesCompletedPer90,
                passCompletionRatio, openPlayKeyPassesPer90, keyPassesPer90, chancesCreatedPer90);
    }

    public static PP6Crosses createPP6CrossesFromRowInHTML(Element row) {
        double openPlayCrossesCompletedPer90 = parseDouble(row.select("td:nth-child(64)").text());
        int openPlayCrossesCompletedRatio = parseInt(row.select("td:nth-child(68)").text());

        return CRUD_Controller.createPP6Crosses(openPlayCrossesCompletedPer90, openPlayCrossesCompletedRatio);
    }

    public static PP7AerialChallenges createPP7AerialChallengesFromRowInHTML(Element row) {
        double keyHeadersPer90 = parseDouble(row.select("td:nth-child(75)").text());
        int headersWonRatio = parseInt(row.select("td:nth-child(76)").text());
        double headersWonPer90 = parseDouble(row.select("td:nth-child(77)").text());

        return CRUD_Controller.createPP7AerialChallenges(keyHeadersPer90, headersWonRatio, headersWonPer90);
    }

    public static PP8Movement createPP8MovementFromRowInHTML(Element row) {
        int foulsAgainst = parseInt(row.select("td:nth-child(81)").text());
        double pressuresCompletedPer90 = parseDouble(row.select("td:nth-child(82)").text());
        double pressuresAttemptedPer90 = parseDouble(row.select("td:nth-child(83)").text());
        int pressuresCompletedRatio = (int) ((pressuresCompletedPer90 / pressuresAttemptedPer90) * 100);
        double sprintsPer90 = parseDouble(row.select("td:nth-child(86)").text());
        double dribblesPer90 = parseDouble(row.select("td:nth-child(87)").text());
        double distanceCoveredPer90 = parseDouble(row.select("td:nth-child(90)").text()); // BROKEN???
        double possessionLostPer90 = parseDouble(row.select("td:nth-child(91)").text());

        return CRUD_Controller.createPP8Movement(foulsAgainst, pressuresCompletedPer90, pressuresAttemptedPer90,
                pressuresCompletedRatio, sprintsPer90, dribblesPer90, distanceCoveredPer90, possessionLostPer90);
    }

    public static PP9TacklesAndInterceptions createPP9TacklesAndInterceptionsFromRowInHTML(Element row) {
        int foulsCommitted = parseInt(row.select("td:nth-child(93)").text());
        double tacklesWonPer90 = parseDouble(row.select("td:nth-child(94)").text());
        int tacklesWonRatio = parseInt(row.select("td:nth-child(97)").text());
        double shotsBlockedPer90 = parseDouble(row.select("td:nth-child(98)").text());
        double possessionWonPer90 = parseDouble(row.select("td:nth-child(100)").text());
        double keyTacklesPer90 = parseDouble(row.select("td:nth-child(101)").text());
        double interceptionsPer90 = parseDouble(row.select("td:nth-child(103)").text());
        double clearancesPer90 = parseDouble(row.select("td:nth-child(105)").text());
        double blocksPer90 = parseDouble(row.select("td:nth-child(107)").text());

        return CRUD_Controller.createPP9TacklesAndInterceptions(foulsCommitted, tacklesWonPer90, tacklesWonRatio,
                shotsBlockedPer90, possessionWonPer90, keyTacklesPer90, interceptionsPer90, clearancesPer90,
                blocksPer90);
    }

    public static PP10Saves createPP10SavesFromRowInHTML(Element row) {
        double savesPer90 = parseDouble(row.select("td:nth-child(109)").text());
        double concededPer90 = parseDouble(row.select("td:nth-child(110)").text());
        double cleanSheetsPer90 = parseDouble(row.select("td:nth-child(112)").text());
        int saveRatio = parseInt(row.select("td:nth-child(117)").text());
        int xsaveRatio = parseInt(row.select("td:nth-child(118)").text());
        double xGoalsPreventedPer90 = parseDouble(row.select("td:nth-child(119)").text());

        return CRUD_Controller.createPP10Saves(savesPer90, concededPer90, cleanSheetsPer90, saveRatio,
                xsaveRatio, xGoalsPreventedPer90);
    }


    private static LocalDate LocalDateFormatter(String date) {
        if (date.equals("-")) return null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(date, dateTimeFormatter);
    }

    private static int parseInt(String string) {
        try {
            if (string.contains("%")) string = string.replace("%", "");
            return Integer.parseInt(string);
        } catch (Exception e) {
            return 0;
        }

    }

    private static double parseDouble(String string) {
        try {
            if (string.contains("km")) string = string.replace("km", "");
            return Double.parseDouble(string);
        } catch (Exception e) {
            return 0;
        }
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
