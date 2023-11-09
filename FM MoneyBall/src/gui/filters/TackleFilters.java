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

public class TackleFilters {
    private static List components = new ArrayList<>();
    private static Label lblTacklesWonPer90 = new Label("Tackles Won/90");
    private static Label lblShotsBlockedPer90 = new Label("Shots Blocked/90");
    private static Label lblPossessionWonPer90 = new Label("Poss. Won/90");
    private static Label lblKeyTacklesPer90 = new Label("Key Tackles/90");
    private static Label lblInterceptionsPer90 = new Label("Interceptions/90");
    private static Label lblClearancesPer90 = new Label("Clearances/90");
    private static Label lblBlocksPer90 = new Label("Blocks/90");
    private static TextField txfTacklesWonPer90 = new TextField();
    private static TextField txfShotsBlockedPer90 = new TextField();
    private static TextField txfPossessionWonPer90 = new TextField();
    private static TextField txfKeyTacklesPer90 = new TextField();
    private static TextField txfInterceptionsPer90 = new TextField();
    private static TextField txfClearancesPer90 = new TextField();
    private static TextField txfBlocksPer90 = new TextField();

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
        components.add(lblTacklesWonPer90);
        components.add(lblShotsBlockedPer90);
        components.add(lblPossessionWonPer90);
        components.add(lblKeyTacklesPer90);
        components.add(lblInterceptionsPer90);
        components.add(lblClearancesPer90);
        components.add(lblBlocksPer90);

        components.add(txfTacklesWonPer90);
        components.add(txfShotsBlockedPer90);
        components.add(txfPossessionWonPer90);
        components.add(txfKeyTacklesPer90);
        components.add(txfInterceptionsPer90);
        components.add(txfClearancesPer90);
        components.add(txfBlocksPer90);
    }

    public static List<Player> applyFilters(List<Player> players) {

        if (!txfTacklesWonPer90.getText().isEmpty())
            players = filterByTacklesWonPer90(players);

        if (!txfShotsBlockedPer90.getText().isEmpty())
            players = filterByShotsBlockedPer90(players);

        if (!txfPossessionWonPer90.getText().isEmpty())
            players = filterByPossessionWonPer90(players);

        if (!txfKeyTacklesPer90.getText().isEmpty())
            players = filterKeyTacklesPer90(players);

        if (!txfInterceptionsPer90.getText().isEmpty())
            players = filterByInterceptionsPer90(players);

        if (!txfClearancesPer90.getText().isEmpty())
            players = filterByClearancesPer90(players);

        if (!txfBlocksPer90.getText().isEmpty())
            players = filterByBlocksPer90(players);

        return players;
    }

    public static void resetFilters() {
        for (Object textField : components) {
            if (textField instanceof TextField) {
                ((TextField) textField).setText("");
            }
        }

    }

    private static List<Player> filterByTacklesWonPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getTacklesWonPer90() >=
                    Double.parseDouble(txfTacklesWonPer90.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterByShotsBlockedPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getShotsBlockedPer90() >=
                    Double.parseDouble(txfShotsBlockedPer90.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterByPossessionWonPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getPossessionWonPer90() >=
                    Double.parseDouble(txfPossessionWonPer90.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterKeyTacklesPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getKeyTacklesPer90() >=
                    Double.parseDouble(txfKeyTacklesPer90.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterByInterceptionsPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getInterceptionsPer90() >=
                    Double.parseDouble(txfInterceptionsPer90.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterByClearancesPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getClearancesPer90() >=
                    Double.parseDouble(txfClearancesPer90.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static List<Player> filterByBlocksPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if ((player.getBlocksPer90() >=
                    Double.parseDouble(txfBlocksPer90.getText())))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static void addTooltips() {
        Tooltip ttTacklesWonPer90 = new Tooltip("Tackles won per 90 minutes");
        txfTacklesWonPer90.setTooltip(ttTacklesWonPer90);
        ttTacklesWonPer90.setShowDelay(Duration.millis(150));

        Tooltip ttShotsBlockedPer90  = new Tooltip("Shots blocked per 90 minutes");
        txfShotsBlockedPer90.setTooltip(ttShotsBlockedPer90);
        ttShotsBlockedPer90.setShowDelay(Duration.millis(150));

        Tooltip ttPossessionWonPer90  = new Tooltip("Possession won per 90 minutes");
        txfPossessionWonPer90.setTooltip(ttPossessionWonPer90);
        ttPossessionWonPer90.setShowDelay(Duration.millis(150));

        Tooltip ttKeyTacklesPer90  = new Tooltip("Key tackles per 90 minutes");
        txfKeyTacklesPer90.setTooltip(ttKeyTacklesPer90);
        ttKeyTacklesPer90.setShowDelay(Duration.millis(150));

        Tooltip ttInterceptionsPer90  = new Tooltip("Interceptions per 90 minutes");
        txfInterceptionsPer90.setTooltip(ttInterceptionsPer90);
        ttInterceptionsPer90.setShowDelay(Duration.millis(150));

        Tooltip ttClearancesPer90  = new Tooltip("Clearances per 90 minutes");
        txfClearancesPer90.setTooltip(ttClearancesPer90);
        ttClearancesPer90.setShowDelay(Duration.millis(150));

        Tooltip ttBlocksPer90  = new Tooltip("Blocks per 90 minutes");
        txfBlocksPer90.setTooltip(ttBlocksPer90);
        ttBlocksPer90.setShowDelay(Duration.millis(150));
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
