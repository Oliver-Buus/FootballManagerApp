package storage;

import model.PP2Universal;
import model.Player;

import java.util.ArrayList;

public class Storage {
    private static final ArrayList<Player> PLAYERS = new ArrayList<>();
    private static final ArrayList<PP2Universal> PP_2_UNIVERSALS = new ArrayList<>();

    // Player
    public static ArrayList<Player> getPlayers() {
        return new ArrayList<>(PLAYERS);
    }
    public static void storePlayer(Player player) {
        PLAYERS.add(player);
    }

    // PP2Univsersal
    public static ArrayList<PP2Universal> getPp2Universals() {
        return new ArrayList<>(PP_2_UNIVERSALS);
    }
    public static void storePP2Universal(PP2Universal pp2Universal) {
        PP_2_UNIVERSALS.add(pp2Universal);
    }
}
