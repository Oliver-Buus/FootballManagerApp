package model;

import controller.CRUD_Controller;
import controller.Controller;
import gui.filters.FilterUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Legeplads {
    public static void main(String[] args) {
//        Controller.createPlayerFromHTML("C:\\Users\\failo\\Documents\\Sports Interactive\\Football Manager 2024\\Nationalities.html");

        FilterUtil.readNationalitiesFile();
        //        File fil = new File("C:\\Users\\failo\\Documents\\Sports Interactive\\Football Manager 2024\\Nationalities.html");
//        List<String> list = new ArrayList<>();
//        try {
//            Document document = Jsoup.parse(fil);
//            for (Element row : document.select("table tr")) {
//                if (row.select("td").text().isEmpty()) continue;
//
//                if (!list.contains(row.select("td:nth-child(6)").text())) {
//                    list.add(row.select("td:nth-child(6)").text());
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//
//
//        try {
//            File file = new File("C:\\Users\\failo\\Documents\\GitHub\\FootballManagerApp\\FM MoneyBall\\src\\resources\\Nationalities.txt");
//            FileWriter myWriter = new FileWriter(file);
//            for (String s : list) {
//                myWriter.write(s + "\n");
//            }
//            myWriter.close();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        for (int i = 0; i < Storage.getPlayers().size(); i++) {
//            Player pl = Storage.getPlayers().get(i);
//            System.out.println(pl.getShotsOnTargetRatio());
//        }
//        for (Player player : Storage.getPlayers()) {
//            if (player.getPositions().contains(Position.MC)) {
//                System.out.println(player.toString());
//            }
//        }
    }
}

