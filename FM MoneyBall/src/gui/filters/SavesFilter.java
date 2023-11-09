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

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class SavesFilter {
    private static List components = new ArrayList<>();
    private static Label lblSavesPer90 = new Label("Saves/90");
    private static Label lblConcededPer90 = new Label("Conceded/90 (max)");
    private static Label lblCleanSheetsPer90 = new Label("Clean Sheets/90");
    private static Label lblSaveRatio = new Label("Save%");
    private static Label lblXSaveRatio = new Label("Expected Save%");
    private static Label lblXGoalsPreventedper90 = new Label("xGoals prev./90");
    private static TextField txfSavesPer90 = new TextField();
    private static TextField txfConcededPer90 = new TextField();
    private static TextField txfCleanSheetsPer90 = new TextField();
    private static TextField txfSaveRatio = new TextField();
    private static TextField txfXSaveRatio = new TextField();
    private static TextField txfXGoalsPreventedper90 = new TextField();

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
        components.add(lblSavesPer90);
        components.add(lblConcededPer90);
        components.add(lblCleanSheetsPer90);
        components.add(lblSaveRatio);
        components.add(lblXSaveRatio);
        components.add(lblXGoalsPreventedper90);

        components.add(txfSavesPer90);
        components.add(txfConcededPer90);
        components.add(txfCleanSheetsPer90);
        components.add(txfSaveRatio);
        components.add(txfXSaveRatio);
        components.add(txfXGoalsPreventedper90);
    }

    public static List<Player> applyFilters(List<Player> players) {
        for (Object textField : components) {
            if (textField instanceof TextField) {
                ((TextField) textField).setText(((TextField)
                        textField).getText().replace(",", "."));
            }
        }

        if (!txfSavesPer90.getText().isEmpty())
            players = filterBySavesPer90(players);

        if (!txfConcededPer90.getText().isEmpty())
            players = filterByConcededPer90(players);

        if (!txfCleanSheetsPer90.getText().isEmpty())
            players = filterByCleanSheetsPer90(players);

        if (!txfSaveRatio.getText().isEmpty())
            players = filterBySaveRatioPer90(players);

        if (!txfXSaveRatio.getText().isEmpty())
            players = filterByXSaveRatioPer90(players);

        if (!txfXGoalsPreventedper90.getText().isEmpty())
            players = filterByXGoalsPreventedPer90(players);

        return players;
    }

    public static void resetFilters() {
        for (Object textField : components) {
            if (textField instanceof TextField) {
                ((TextField) textField).setText("");
            }
        }

    }

    private static List<Player> filterBySavesPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getSavesPer90() >=
                    Double.parseDouble(txfSavesPer90.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }
    private static List<Player> filterByConcededPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getConcededPer90() <=
                    Double.parseDouble(txfConcededPer90.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterByCleanSheetsPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getCleanSheetsPer90() >=
                    Double.parseDouble(txfCleanSheetsPer90.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterBySaveRatioPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getSaveRatio() >=
                    Double.parseDouble(txfSaveRatio.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterByXSaveRatioPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getXsaveRatio() >=
                    Double.parseDouble(txfXSaveRatio.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterByXGoalsPreventedPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getXGoalsPreventedPer90() >=
                    Double.parseDouble(txfXGoalsPreventedper90.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static void addTooltips() {
        Tooltip ttSavesPer90 = new Tooltip("Pressures completed per 90 minutes");
        txfSavesPer90.setTooltip(ttSavesPer90);
        ttSavesPer90.setShowDelay(Duration.millis(150));

        Tooltip ttConcededPer90  = new Tooltip("Pressures attempted per 90 minutes");
        txfConcededPer90.setTooltip(ttConcededPer90);
        ttConcededPer90.setShowDelay(Duration.millis(150));

        Tooltip ttCleanSheetsPer90  = new Tooltip("High intensity sprints per 90 minutes");
        txfCleanSheetsPer90.setTooltip(ttCleanSheetsPer90);
        ttCleanSheetsPer90.setShowDelay(Duration.millis(150));

        Tooltip ttSaveRatio  = new Tooltip("Dribbles made per 90 minutes");
        txfSaveRatio.setTooltip(ttSaveRatio);
        ttSaveRatio.setShowDelay(Duration.millis(150));

        Tooltip ttXSaveRatio  = new Tooltip("Distance covered per 90 minutes");
        txfXSaveRatio.setTooltip(ttXSaveRatio);
        ttXSaveRatio.setShowDelay(Duration.millis(150));

        Tooltip ttXGoalsPreventedper90  = new Tooltip("Possession lost per 90 minutes");
        txfXGoalsPreventedper90.setTooltip(ttXGoalsPreventedper90);
        ttXGoalsPreventedper90.setShowDelay(Duration.millis(150));
    }

    private static void restrictInput() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("^[0-9\\,.-]*$")) return change;
            return null;
        };
        for (Object textField : components) {
            if (textField instanceof TextField) {
                ((TextField) textField).setTextFormatter(new TextFormatter<>(filter));
            }
        }
    }
}
