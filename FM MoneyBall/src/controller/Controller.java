package controller;

import model.PP2Universal;
import model.Player;
import model.Position;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import storage.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    public static Player createPlayer(String division, String club, String name, int age, String positionString,
                                      String nationality, String secondNationality, int height, String personality,
                                      String recurringInjury, String euNational, String homeGrownStatus,
                                      String preferredFoot, String wage, String contractExpiryDate,
                                      String transferStatus, String transferValue, String wpNeeded, String wpChance) {
        Player player = new Player(division, club, name, age, positionString, nationality, secondNationality,
                height, personality, recurringInjury, euNational, homeGrownStatus, preferredFoot, wage,
                contractExpiryDate, transferStatus, transferValue, wpNeeded, wpChance);
        player.addPositionsToPlayer();
        Storage.storePlayer(player);
        return player;
    }

    public static ArrayList<Player> getAllPlayers() {
        return Storage.getPlayers();
    }

    public static PP2Universal createPP2Universal(Player player, String apps, int mins, double minsPerGame,
                                                  int playerOfTheMatch, int goalLeadingMistakes, double avgRating) {

        PP2Universal pp2Universal = new PP2Universal(apps, mins, minsPerGame, playerOfTheMatch,
                goalLeadingMistakes, avgRating);
        player.setPp2Universal(pp2Universal);
        Storage.storePP2Universal(pp2Universal);

        return pp2Universal;
    }

    public static void createPlayerFromHTML(String filePath) {
        File file = new File("C:\\Users\\failo\\Documents\\Sports Interactive\\" +
                "Football Manager 2024\\AllColumns.html");
        //file = new File(filePath);

        try {
            Document document = Jsoup.parse(file);

            int count = 0;
            for (Element heading : document.select("table th")) {
                count++;
//                System.out.println(count + " " + heading.text());
                System.out.println(heading.text());
            }



            for (Element row : document.select("table tr")) {
                if (row.select("td").text().isEmpty()) continue;

                String division = row.select("td:nth-child(1)").text();
                String club = row.select("td:nth-child(2)").text();
                String name = row.select("td:nth-child(3)").text();
                int age = Integer.parseInt(row.select("td:nth-child(4)").text());
                String positionString = row.select("td:nth-child(5)").text();
                String nationality = row.select("td:nth-child(6)").text();
                String secondNationality = row.select("td:nth-child(7)").text();

                String heightString = row.select("td:nth-child(8)").text();
                heightString = heightString.replaceAll("\\s.*", "");
                int height = Integer.parseInt(heightString);

                String personality = row.select("td:nth-child(9)").text();
                String recurringInjury = row.select("td:nth-child(10)").text();
                String euNational = row.select("td:nth-child(11)").text();
                String homeGrownStatus = row.select("td:nth-child(12)").text();
                String prefFoot = row.select("td:nth-child(13)").text();
                String wage = row.select("td:nth-child(14)").text();
                String expires = row.select("td:nth-child(15)").text();
                String transferStatus = row.select("td:nth-child(16)").text();
                String transferValue = row.select("td:nth-child(17)").text();
                String wpNeeded = row.select("td:nth-child(18)").text();
                String wpChance = row.select("td:nth-child(19)").text();

                Player player = createPlayer(division, club, name, age, positionString, nationality, secondNationality,
                        height, personality, recurringInjury, euNational, homeGrownStatus, prefFoot, wage,
                        expires, transferStatus, transferValue, wpNeeded, wpChance);
                //createPP2Universal(player, );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
