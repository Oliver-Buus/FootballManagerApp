package controller;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Controller {
    public static Player addPPsToPlayer(Player player, PP2Universal pp2Universal, PP3CustomStats pp3CustomStats,
                                        PP4Shots pp4Shots, PP5Passes pp5Passes, PP6Crosses pp6Crosses,
                                        PP7AerialChallenges pp7AerialChallenges, PP8Movement pp8Movement,
                                        PP9TacklesAndInterceptions pp9TacklesAndInterceptions, PP10Saves pp10Saves) {

        player.setPp2Universal(pp2Universal);
        player.setPp3CustomStats(pp3CustomStats);
        player.setPp4Shots(pp4Shots);
        player.setPp5Passes(pp5Passes);
        player.setPp6Crosses(pp6Crosses);
        player.setPp7AerialChallenges(pp7AerialChallenges);
        player.setPp8Movement(pp8Movement);
        player.setPp9TacklesAndInterceptions(pp9TacklesAndInterceptions);
        player.setPp10Saves(pp10Saves);

        return player;
    }

    public static boolean openFolder(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file");

        File initialDirectory = new File(System.getProperty("user.home") + "\\Documents\\Sports Interactive\\Football Manager 2024");

        if (initialDirectory.exists()) fileChooser.setInitialDirectory(initialDirectory);

        File selectedFile = fileChooser.showOpenDialog(stage);


        if (!(selectedFile == null)) {
            String filePath = selectedFile.getAbsolutePath();
            System.out.println("Chosen path: " + filePath);
            createPlayerFromHTML(filePath);
            return true;
        } else return false;

    }

    public static void createPlayerFromHTML(String filePath) {
//        File file = new File("C:\\Users\\failo\\Documents\\Sports Interactive\\" +
//                "Football Manager 2024\\AllColumns.html");
        File file = new File(filePath);

        try {
            Document document = Jsoup.parse(file);

//            int count = 0;
//            for (Element heading : document.select("table th")) {
//                count++;
//                System.out.println(count + " " + heading.text());
//            }

                CRUD_Controller.removeAllPlayers();
            for (Element row : document.select("table tr")) {
                if (row.select("td").text().isEmpty()) continue;

                Player player = ReadFromHTML_Controller.createPlayerFromRowInHTML(row);
                addPPsToPlayer(player, null, null, null, null, null,
                        null, null, null, null);
                PP2Universal pp2Universal = ReadFromHTML_Controller.createPP2UniversalFromRowInHTML(row);
                addPPsToPlayer(player, pp2Universal, null, null, null, null,
                        null, null, null, null);
                PP4Shots pp4Shots = ReadFromHTML_Controller.createPP4ShotsFromRowInHTML(row);
                addPPsToPlayer(player, pp2Universal, null, pp4Shots, null, null,
                        null, null, null, null);
                PP5Passes pp5Passes = ReadFromHTML_Controller.createPP5PassesFromRowInHTML(row);
                addPPsToPlayer(player, pp2Universal, null, pp4Shots, pp5Passes, null,
                        null, null, null, null);
                PP6Crosses pp6Crosses = ReadFromHTML_Controller.createPP6CrossesFromRowInHTML(row);
                addPPsToPlayer(player, pp2Universal, null, pp4Shots, pp5Passes, pp6Crosses,
                        null, null, null, null);
                PP7AerialChallenges pp7AerialChallenges =
                        ReadFromHTML_Controller.createPP7AerialChallengesFromRowInHTML(row);
                addPPsToPlayer(player, pp2Universal, null, pp4Shots, pp5Passes, pp6Crosses,
                        pp7AerialChallenges, null, null, null);
                PP8Movement pp8Movement = ReadFromHTML_Controller.createPP8MovementFromRowInHTML(row);
                addPPsToPlayer(player, pp2Universal, null, pp4Shots, pp5Passes, pp6Crosses,
                        pp7AerialChallenges, pp8Movement, null, null);
                PP9TacklesAndInterceptions pp9TacklesAndInterceptions =
                        ReadFromHTML_Controller.createPP9TacklesAndInterceptionsFromRowInHTML(row);
                addPPsToPlayer(player, pp2Universal, null, pp4Shots, pp5Passes, pp6Crosses,
                        pp7AerialChallenges, pp8Movement, pp9TacklesAndInterceptions, null);
                PP10Saves pp10Saves = ReadFromHTML_Controller.createPP10SavesFromRowInHTML(row);
                addPPsToPlayer(player, pp2Universal, null, pp4Shots, pp5Passes, pp6Crosses,
                        pp7AerialChallenges, pp8Movement, pp9TacklesAndInterceptions, pp10Saves);

                PP3CustomStats pp3CustomStats = ReadFromHTML_Controller.createPP3CustomStatsFromRowInHTML(row, player);
                addPPsToPlayer(player, pp2Universal, pp3CustomStats, pp4Shots, pp5Passes, pp6Crosses,
                        pp7AerialChallenges, pp8Movement, pp9TacklesAndInterceptions, pp10Saves);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
