package storage;

import model.Player;

import java.util.ArrayList;

public class Storage {
    private static final ArrayList<Player> PLAYERS = new ArrayList<>();

    // PLAYER
    public static ArrayList<Player> getPlayers() {
        return new ArrayList<>(PLAYERS);
    }
    public static void storePlayer(Player player) {
        PLAYERS.add(player);
    }
}
