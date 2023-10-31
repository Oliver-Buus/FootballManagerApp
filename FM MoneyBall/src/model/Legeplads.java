package model;

import storage.Storage;

public class Legeplads {
    public static void main(String[] args) {
        for (Player player : Storage.getPlayers()) {
            if (player.getPositions().contains(Position.MC)) {
                System.out.println(player.toString());
            }
        }
    }
}

