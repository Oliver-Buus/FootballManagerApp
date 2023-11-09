package gui.filters;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import model.Player;
import model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class MovementFilters {
    private static List components = new ArrayList<>();
    private static Label lblPressuresCompletedPer90 = new Label("Pres Compl./90");
    private static Label lblPressuresAttemptedPer90 = new Label("Pres Atmp./90");
    private static Label lblHighIntensitySprintsPer90 = new Label("Sprints/90");
    private static Label lblDribblesPer90 = new Label("Dribbles/90");
    private static Label lblDistanceCoveredPer90 = new Label("Dist/90");
    private static Label lblPossessionLostPer90 = new Label("Poss. Lost/90 (max)");
    private static TextField txfPressuresCompletedPer90 = new TextField();
    private static TextField txfPressuresAttemptedPer90 = new TextField();
    private static TextField txfHighIntensitySprintsPer90 = new TextField();
    private static TextField txfDribblesPer90 = new TextField();
    private static TextField txfDistanceCoveredPer90 = new TextField();
    private static TextField txfPossessionLostPer90 = new TextField();



    public static GridPane addToGridPane() {
        GridPane gridPane = new GridPane();
        addAllComponentsToList();
        addTooltips();
        restrictInput();

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
        components.add(lblPressuresCompletedPer90);
        components.add(lblPressuresAttemptedPer90);
        components.add(lblHighIntensitySprintsPer90);
        components.add(lblDribblesPer90);
        components.add(lblDistanceCoveredPer90);
        components.add(lblPossessionLostPer90);

        components.add(txfPressuresCompletedPer90);
        components.add(txfPressuresAttemptedPer90);
        components.add(txfHighIntensitySprintsPer90);
        components.add(txfDribblesPer90);
        components.add(txfDistanceCoveredPer90);
        components.add(txfPossessionLostPer90);
    }

    public static List<Player> applyFilters(List<Player> players) {

        if (!txfPressuresCompletedPer90.getText().isEmpty())
            players = filterByPressuresCompletedPer90(players);

        if (!txfPressuresAttemptedPer90.getText().isEmpty())
            players = filterByPressuresAttemptedPer90(players);

        if (!txfHighIntensitySprintsPer90.getText().isEmpty())
            players = filterBySprintsPer90(players);

        if (!txfDribblesPer90.getText().isEmpty())
            players = filterByDribblesPer90(players);

        if (!txfDistanceCoveredPer90.getText().isEmpty())
            players = filterByDistanceCoveredPer90(players);

        if (!txfPossessionLostPer90.getText().isEmpty())
            players = filterByPossessionLostPer90(players);

        return players;
    }

    public static void resetFilters() {
        for (Object textField : components) {
            if (textField instanceof TextField) {
                ((TextField) textField).setText("");
            }
        }

    }

    private static List<Player> filterByPressuresCompletedPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getPressuresCompletedPer90() >= Double.parseDouble(txfPressuresCompletedPer90.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterByPressuresAttemptedPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getPressuresAttemptedPer90() >= Double.parseDouble(txfPressuresAttemptedPer90.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterBySprintsPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getSprintsPer90() >= Double.parseDouble(txfHighIntensitySprintsPer90.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterByDribblesPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getDribblesPer90() >= Double.parseDouble(txfDribblesPer90.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterByDistanceCoveredPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getDistanceCoveredPer90() >= Double.parseDouble(txfDistanceCoveredPer90.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterByPossessionLostPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getPossessionLostPer90() <= Double.parseDouble(txfPossessionLostPer90.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static void addTooltips() {
        Tooltip ttPressuresCompletedPer90 = new Tooltip("Pressures completed per 90 minutes");
        txfPressuresCompletedPer90.setTooltip(ttPressuresCompletedPer90);
        ttPressuresCompletedPer90.setShowDelay(Duration.millis(150));

        Tooltip ttPressuresAttemptedPer90  = new Tooltip("Pressures attempted per 90 minutes");
        txfPressuresAttemptedPer90.setTooltip(ttPressuresAttemptedPer90);
        ttPressuresAttemptedPer90.setShowDelay(Duration.millis(150));

        Tooltip ttHighIntensitySprintsPer90  = new Tooltip("High intensity sprints per 90 minutes");
        txfHighIntensitySprintsPer90.setTooltip(ttHighIntensitySprintsPer90);
        ttHighIntensitySprintsPer90.setShowDelay(Duration.millis(150));

        Tooltip ttDribblesPer90  = new Tooltip("Dribbles made per 90 minutes");
        txfDribblesPer90.setTooltip(ttDribblesPer90);
        ttDribblesPer90.setShowDelay(Duration.millis(150));

        Tooltip ttDistanceCoveredPer90  = new Tooltip("Distance covered per 90 minutes");
        txfDistanceCoveredPer90.setTooltip(ttDistanceCoveredPer90);
        ttDistanceCoveredPer90.setShowDelay(Duration.millis(150));

        Tooltip ttPossessionLostPer90  = new Tooltip("Possession lost per 90 minutes");
        txfPossessionLostPer90.setTooltip(ttPossessionLostPer90);
        ttPossessionLostPer90.setShowDelay(Duration.millis(150));
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
