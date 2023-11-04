package model;

import controller.Controller;
import storage.Storage;

public class Legeplads {
    public static void main(String[] args) {
        Controller.createPlayerFromHTML("");
        for (int i = 0; i < Storage.getPlayers().size(); i++) {
            Player pl = Storage.getPlayers().get(i);
            System.out.println(pl.getShotsOnTargetRatio());
        }
//        for (Player player : Storage.getPlayers()) {
//            if (player.getPositions().contains(Position.MC)) {
//                System.out.println(player.toString());
//            }
//        }
    }
}

