package gui;

import controller.Controller;
import model.Player;
import model.Position;
import storage.Storage;

public class App {
    public static void main(String[] args) {
        Controller.createPlayerFromHTML("");
        Menu.launch(Menu.class);

        for (Player player : Storage.getPlayers()) {
            if (player.getPositions().contains(Position.MC)) {
                System.out.println(player.toString());
            }
            if (player.getName().contains("Brekalo")) {
                System.out.println(player.toString());
            }
        }
    }



}
