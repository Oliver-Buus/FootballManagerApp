package gui.filters;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class ShotFilters {

    private static List components = new ArrayList<>();
    private static Label lblGoalsPer90 = new Label("Goals/90");
    private static Label lblNpxGPer90 = new Label("npxG/90");
    private static Label lblShotsPer90 = new Label("Shots/90");
    private static Label lblShotsOnTargetPer90 = new Label("Shots on Target/90");
    private static Label lblXGPerShot = new Label("xG/Shot");
    private static TextField txfGoalsPer90 = new TextField();
    private static TextField txfNpxGPer90 = new TextField();
    private static TextField txfShotsPer90 = new TextField();
    private static TextField txfShotsOnTargetPer90 = new TextField();
    private static TextField txfXGPerShot = new TextField();





    public static GridPane addToGridPane() {
        GridPane gridPane = new GridPane();
        addAllComponentsToList();
        restrictInput();
        addTooltips();

        gridPane.getStyleClass().add("custom-gridpane");
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        Label lblMin = new Label("Min.");
        GridPane.setHalignment(lblMin, HPos.CENTER);
        gridPane.add(lblMin, 1, 0);

        int i = 1;
        for (Object label : components) {
            if (label instanceof Label) {
                ((Label) label).setPrefWidth(120);
                gridPane.add((Node) label, 0, i);
                i++;
            }
        }

        i = 1;
        for (Object textField : components) {
            if (textField instanceof TextField) {
                ((TextField) textField).setPrefWidth(50);
                ((TextField) textField).setMaxWidth(50);
                ((TextField) textField).setStyle("-fx-alignment: CENTER;");
                gridPane.add((Node) textField, 1, i);
                i++;
            }
        }

        return gridPane;
    }

    private static void addAllComponentsToList() {
        components.add(lblGoalsPer90);
        components.add(lblNpxGPer90);
        components.add(lblShotsPer90);
        components.add(lblShotsOnTargetPer90);
        components.add(lblXGPerShot);


        components.add(txfGoalsPer90);
        components.add(txfNpxGPer90);
        components.add(txfShotsPer90);
        components.add(txfShotsOnTargetPer90);
        components.add(txfXGPerShot);

    }


    public static List<Player> applyFilters(List<Player> players) {
        if (!txfGoalsPer90.getText().isEmpty())
            players = filterByGoalsPer90(players);

        if (!txfNpxGPer90.getText().isEmpty())
            players = filterByNpxGPer90(players);

        if (!txfShotsPer90.getText().isEmpty())
            players = filterByShotsPer90(players);



        if (!txfShotsOnTargetPer90.getText().isEmpty())
            players = filterByShotsOnTargetPer90(players);



        if (!txfXGPerShot.getText().isEmpty())
            players = filterByXGPerShot(players);



        return players;
    }

    public static void resetFilters() {
        txfGoalsPer90.setText("");
        txfNpxGPer90.setText("");
        txfShotsPer90.setText("");
        txfShotsOnTargetPer90.setText("");
        txfXGPerShot.setText("");

    }

    private static List<Player> filterByGoalsPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();

        for (Player player : players) {
            if (player.getGlsPer90() >= Double.parseDouble(txfGoalsPer90.getText()))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterByNpxGPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();

        for (Player player : players) {
            if (player.getNpxGPer90() >= Double.parseDouble(txfNpxGPer90.getText()))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterByShotsPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();

        for (Player player : players) {
            if (player.getShotsPer90() >= Double.parseDouble(txfShotsPer90.getText()))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterByShotsOnTargetPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();

        for (Player player : players) {
            if (player.getShotsOnTargetPer90() >= Double.parseDouble(txfShotsOnTargetPer90.getText()))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterByXGPerShot(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();

        for (Player player : players) {
            if (player.getXGPerShot() >= Double.parseDouble(txfXGPerShot.getText()))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static void addTooltips() {
        Tooltip ttGoalsPer90 = new Tooltip("Goals scored per 90 minutes");
        txfGoalsPer90.setTooltip(ttGoalsPer90);
        ttGoalsPer90.setShowDelay(Duration.millis(150));

        Tooltip ttNpxGPer90 = new Tooltip("Non-penalty expected goals per 90 minutes");
        txfNpxGPer90.setTooltip(ttNpxGPer90);
        ttNpxGPer90.setShowDelay(Duration.millis(150));

        Tooltip ttShotsPer90 = new Tooltip("Shots per 90 minutes");
        txfShotsPer90.setTooltip(ttShotsPer90);
        ttShotsPer90.setShowDelay(Duration.millis(150));

        Tooltip ttShotsOnTargetPer90 = new Tooltip("Shots on target per 90 minutes");
        txfShotsOnTargetPer90.setTooltip(ttShotsOnTargetPer90);
        ttShotsOnTargetPer90.setShowDelay(Duration.millis(150));

        Tooltip ttXGPerShot = new Tooltip("Expected goals per shot taken");
        txfXGPerShot.setTooltip(ttXGPerShot);
        ttXGPerShot.setShowDelay(Duration.millis(150));

    }

    private static void restrictInput() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*\\.?[0-9]*")) return change;
            return null;
        };
        for (Object textField : components) {
            if (textField instanceof TextField) {
                ((TextField) textField).setTextFormatter(new TextFormatter<>(filter));
            }
        }
    }



}
