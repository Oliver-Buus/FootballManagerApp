package gui;

import javafx.scene.control.TextField;
import model.Player;
import model.Position;

import java.util.ArrayList;
import java.util.List;

public class GuiFilters {
    public static List<Player> filterByPosition(List<Player> players, String selectedPosition) {
        List<Player> filteredData = new ArrayList<>();

        for (Player player : players) {
            for (Position position : player.getPositions()) {
                if (position.toString().equals(selectedPosition)) {
                    filteredData.add(player);
                    break;
                }
            }
        }

        return filteredData;
    }

    public static List<Player> filterByAge(List<Player> players, TextField txfAgeMin, TextField txfAgeMax) {
        if (txfAgeMin.getText().isEmpty()) txfAgeMin.setText("0");
        else if (txfAgeMax.getText().isEmpty()) txfAgeMax.setText("100");

        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if (player.getAge() >= Integer.parseInt(txfAgeMin.getText()) &&
                    player.getAge() <= Integer.parseInt(txfAgeMax.getText())) {
                filteredData.add(player);
            }
        }

        return filteredData;
    }


    public static List<Player> filterByHeight(List<Player> players, TextField txfHeightMin, TextField txfHeightMax) {
        if (txfHeightMin.getText().isEmpty()) txfHeightMin.setText("100");
        else if (txfHeightMax.getText().isEmpty()) txfHeightMax.setText("250");

        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if (player.getHeight() >= Integer.parseInt(txfHeightMin.getText()) &&
                    player.getHeight() <= Integer.parseInt(txfHeightMax.getText())) {
                filteredData.add(player);
            }
        }

        return filteredData;
    }
}
