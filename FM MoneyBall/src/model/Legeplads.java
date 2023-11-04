package model;

import controller.CRUD_Controller;
import controller.Controller;

public class Legeplads {
    public static void main(String[] args) {
        Controller.createPlayerFromHTML("C:\\Users\\failo\\Documents\\Sports Interactive\\Football Manager 2024\\AllPremierLeague.html");

        for (int i = 0; i < 5; i++) {
            Player player = CRUD_Controller.getAllPlayers().get(i);
            System.out.println(player.toString());

        }
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

