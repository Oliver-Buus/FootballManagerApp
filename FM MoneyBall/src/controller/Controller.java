package controller;

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

    public static Player createPlayer(String name, int age, String positionString,
                                      String nationality, int height, int weight, String personality,
                                      String injurySusceptibility, String recurringInjury) {
        Player player = new Player(name, age, positionString, nationality, height, weight, personality,
                injurySusceptibility, recurringInjury);
        addPositionsToPlayer(player);
        Storage.storePlayer(player);
        return player;
    }

    public static ArrayList<Player> getAllPlayers() {
        return Storage.getPlayers();
    }

    public static void addPositionsToPlayer(Player player) {
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

        Position[] positions = new Position[13];
        positions[0] = Position.DR;
        positions[1] = Position.DL;
        positions[2] = Position.DC;
        positions[3] = Position.WBR;
        positions[4] = Position.WBL;
        positions[5] = Position.DM;
        positions[6] = Position.MR;
        positions[7] = Position.ML;
        positions[8] = Position.MC;
        positions[9] = Position.AMR;
        positions[10] = Position.AML;
        positions[11] = Position.AMC;
        positions[12] = Position.STC;

        for (int i = 0; i < files.length; i++) {
            try {
                Scanner reader = new Scanner(files[i]);

                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    if (line.equals(player.getPositionString()))
                        player.addPosition(positions[i]);
                    else if (player.getPositionString().contains("GK"))
                        player.addPosition(Position.GK);
                }
                reader.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void createPlayerFromHTML(String filePath) {
        File file = new File("C:\\Users\\failo\\Documents\\Sports Interactive\\" +
                "Football Manager 2024\\Test.html");
        //file = new File(filePath);

        try {
            Document document = Jsoup.parse(file);


            int count = 0;
            for (Element heading : document.select("table th")) {
                count++;
                System.out.println(count + " " + heading.text());
            }



            for (Element row : document.select("table tr")) {
                if (row.select("td").text().isEmpty()) continue;
                String name = row.select("td:nth-child(1)").text();

                int age = Integer.parseInt(row.select("td:nth-child(2)").text());

                String positionString = row.select("td:nth-child(3)").text();

                String nationality = row.select("td:nth-child(4)").text();

                String heightString = row.select("td:nth-child(5)").text();
                heightString = heightString.replaceAll("\\s.*", "");
                int height = Integer.parseInt(heightString);

                String weightString = row.select("td:nth-child(6)").text();
                weightString = weightString.replaceAll("\\s.*", "");
                int weight = Integer.parseInt(weightString);

                String personality = row.select("td:nth-child(7)").text();

                String injurySusceptibility = row.select("td:nth-child(8)").text();

                String recurringInjury = row.select("td:nth-child(9)").text();

                createPlayer(name, age, positionString, nationality, height, weight,
                        personality, injurySusceptibility, recurringInjury);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
