package gui;

import controller.CRUD_Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Player;
import model.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;

public class GuiFilters {
    private static GridPane gridPane = new GridPane();
    private static TitledPane titledPane = new TitledPane("Filters", gridPane);
    private static Button btnFilter = new Button("Apply");
    private static Button btnResetFilters = new Button("Reset");

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

    public static TitledPane makeFilters(TableView tableView, Label playerAmount) {
        addToCbbPlayerPosition();
        modifyNodes();
        addToGridPane();

        btnFilter.setOnAction(event -> applyFilters(tableView, playerAmount));
        btnResetFilters.setOnAction(event -> resetFilters(tableView, playerAmount));

        return titledPane;
    }






    private static void modifyNodes() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) return change;
            return null;
        };
        txfAgeMin.setTextFormatter(new TextFormatter<>(filter));
        txfAgeMax.setTextFormatter(new TextFormatter<>(filter));
        txfHeightMin.setTextFormatter(new TextFormatter<>(filter));
        txfHeightMax.setTextFormatter(new TextFormatter<>(filter));
        txfMinutesMin.setTextFormatter(new TextFormatter<>(filter));

        gridPane.getStyleClass().add("custom-gridpane");
        cbbPlayerPositions.setValue("Any");

        txfAgeMin.setPromptText("15");
        txfAgeMin.setMaxWidth(35);
        txfAgeMin.setStyle("-fx-alignment: CENTER;");

        txfAgeMax.setPromptText("45");
        txfAgeMax.setMaxWidth(35);
        txfAgeMax.setStyle("-fx-alignment: CENTER;");
        GridPane.setMargin(txfAgeMax, new Insets(0, 0, 0, 55));

        txfHeightMin.setPromptText("100");
        txfHeightMin.setMaxWidth(35);
        txfHeightMin.setStyle("-fx-alignment: CENTER;");
        txfHeightMax.setPromptText("250");
        txfHeightMax.setMaxWidth(35);
        txfHeightMax.setStyle("-fx-alignment: CENTER;");
        GridPane.setMargin(txfHeightMax, new Insets(0, 0, 0, 55));

        txfMinutesMin.setPromptText("1000");
        txfMinutesMin.setMaxWidth(50);
        txfMinutesMin.setStyle("-fx-alignment: CENTER;");


    }



    private static void addToGridPane() {
        gridPane.setHgap(10);
        gridPane.setVgap(10);

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

        gridPane.add(btnFilter, 0, 10);
        gridPane.add(btnResetFilters, 1, 10);



    }

    public static void applyFilters(TableView tableView, Label playerAmount) {
        List<Player> currentPlayersList = new ArrayList<>(CRUD_Controller.getAllPlayers());

        if (!txfAgeMin.getText().isEmpty() || !txfAgeMax.getText().isEmpty())
            currentPlayersList = filterByAge(currentPlayersList, txfAgeMin, txfAgeMax);

        if (!cbbPlayerPositions.getValue().equals("Any"))
            currentPlayersList = filterByPosition(currentPlayersList, cbbPlayerPositions.getValue());

        if (!txfHeightMin.getText().isEmpty() || !txfHeightMax.getText().isEmpty())
            currentPlayersList = filterByHeight(currentPlayersList, txfHeightMin, txfHeightMax);

        if (!txfMinutesMin.getText().isEmpty())
            currentPlayersList = filterByMinutesPlayed(currentPlayersList, txfMinutesMin);


        // TODO Filters for nationality and more

        tableView.getItems().setAll(currentPlayersList);
        playerAmount.setText("Players: " + tableView.getItems().size());

    }

    public static void resetFilters(TableView tableView, Label playerAmount) {
        tableView.getItems().setAll(CRUD_Controller.getAllPlayers());
        cbbPlayerPositions.setValue("Any");
        txfHeightMin.setText("");
        txfHeightMax.setText("");
        txfAgeMin.setText("");
        txfAgeMax.setText("");
        txfMinutesMin.setText("");
        playerAmount.setText("Players: " + tableView.getItems().size());
    }

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

    public static List<Player> filterByMinutesPlayed(List<Player> players, TextField txfMinutesMin) {
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
