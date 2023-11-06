package gui.filters;

import controller.CRUD_Controller;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Player;
import model.Position;

import java.util.ArrayList;
import java.util.List;

public class BaseFilters {
    private static List components = new ArrayList<>();
    private static Label lblPosition = new Label("Position");
    private static Label lblAge = new Label("Age");
    private static Label lblHeight = new Label("Height");
    private static Label lblMinutes = new Label("Min. Minutes");

    private static ComboBox<String> cbbPlayerPositions = new ComboBox<>();
    private static TextField txfAgeMin = new TextField();
    private static TextField txfAgeMax = new TextField();
    private static TextField txfHeightMin = new TextField();
    private static TextField txfHeightMax = new TextField();
    private static TextField txfMinutesMin = new TextField();

    public static GridPane addToGridPane(GridPane gridPane) {
        addAllComponentsToList();
        modifyComponents();
        addToCbbPlayerPosition();

        gridPane.add(lblPosition, 0, 0);
        gridPane.add(lblAge, 0, 1);
        gridPane.add(lblHeight, 0, 2);
        gridPane.add(lblMinutes, 0, 3);

        gridPane.add(cbbPlayerPositions, 1, 0);
        gridPane.add(txfAgeMin, 1, 1);
        gridPane.add(txfAgeMax, 1, 1);
        gridPane.add(txfHeightMin, 1, 2);
        gridPane.add(txfHeightMax, 1, 2);
        gridPane.add(txfMinutesMin, 1, 3);

        return gridPane;
    }

    private static void modifyComponents() {
        for (Object textField : components) {
            if (textField instanceof TextField) {
                ((TextField) textField).setMaxWidth(40);
                ((TextField) textField).setStyle("-fx-alignment: CENTER;");
            }
        }
        cbbPlayerPositions.setValue("Any");

        txfAgeMin.setPromptText("15");
        txfAgeMax.setPromptText("45");
        txfHeightMin.setPromptText("150");
        txfHeightMax.setPromptText("200");
        txfMinutesMin.setPromptText("100");

        GridPane.setMargin(txfAgeMax, new Insets(0, 0, 0, 55));
        GridPane.setMargin(txfHeightMax, new Insets(0, 0, 0, 55));

    }

    private static void addAllComponentsToList() {
        components.add(lblPosition);
        components.add(lblAge);
        components.add(lblHeight);
        components.add(lblMinutes);
        components.add(cbbPlayerPositions);
        components.add(txfAgeMin);
        components.add(txfAgeMax);
        components.add(txfHeightMin);
        components.add(txfHeightMax);
        components.add(txfMinutesMin);
    }

    public static List<Player> applyFilters(List<Player> players) {

        if (!cbbPlayerPositions.getValue().equals("Any"))
            players = filterByPosition(players);

        if (!txfAgeMin.getText().isEmpty() || !txfAgeMax.getText().isEmpty())
            players = filterByAge(players);

        if (!txfHeightMin.getText().isEmpty() || !txfHeightMax.getText().isEmpty())
            players = filterByHeight(players);

        if (!txfMinutesMin.getText().isEmpty())
            players = filterByMinutesPlayed(players);

        return players;
    }

    public static void resetFilters() {
        cbbPlayerPositions.setValue("Any");
        txfHeightMin.setText("");
        txfHeightMax.setText("");
        txfAgeMin.setText("");
        txfAgeMax.setText("");
        txfMinutesMin.setText("");

    }

    private static List<Player> filterByPosition(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();

        for (Player player : players) {
            for (Position position : player.getPositions()) {
                if (position.toString().equals(cbbPlayerPositions.getValue())) {
                    filteredData.add(player);
                    break;
                }
            }
        }

        return filteredData;
    }

    private static List<Player> filterByAge(List<Player> players) {
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

    private static List<Player> filterByHeight(List<Player> players) {
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

    private static List<Player> filterByMinutesPlayed(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getMins() >= Integer.parseInt(txfMinutesMin.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static void addToCbbPlayerPosition() {
        cbbPlayerPositions.getItems().add("Any");
        for (Position p : Position.values()) {
            cbbPlayerPositions.getItems().add(p.toString());
        }
    }
}
