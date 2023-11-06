package gui.filters;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class ShotFilters implements Filter {

    private static List components = new ArrayList<>();
    private static Label lblGoalsPer90 = new Label("Min. Goals/90");
    private static Label lblNpxGPer90 = new Label("Min. npxG/90");
    private static Label lblShotsPer90 = new Label("Min. Shots/90");
    private static Label lblShotsOnTargetRatio = new Label("Min. Shots on Target%");
    private static Label lblShotsOnTargetPer90 = new Label("Min. Shots on Target/90");
    private static Label lblShotsOutsideBoxPer90 = new Label("Min. Shots Outside Box/90");
    private static Label lblXGPerShot = new Label("Min. xG/Shot");
    private static Label lblConversionRatio = new Label("Min Conversion%");
    private static TextField txfGoalsPer90 = new TextField();
    private static TextField txfNpxGPer90 = new TextField();
    private static TextField txfShotsPer90 = new TextField();
    private static TextField txfShotsOnTargetRatio = new TextField();
    private static TextField txfShotsOnTargetPer90 = new TextField();
    private static TextField txfShotsOutsideBoxPer90 = new TextField();
    private static TextField txfXGPerShot = new TextField();
    private static TextField txfConversionRatio = new TextField();




    public static GridPane addToGridPane() {
        GridPane gridPane = new GridPane();
        addAllComponentsToList();
        modifyComponents();
        restrictInput();
        gridPane.getStyleClass().add("custom-gridpane");

        gridPane.add(lblGoalsPer90, 0, 0);
        gridPane.add(lblNpxGPer90, 0, 1);
        gridPane.add(lblShotsPer90, 0, 2);
        gridPane.add(lblShotsOnTargetRatio, 0, 3);
        gridPane.add(lblShotsOnTargetPer90, 0, 4);
        gridPane.add(lblShotsOutsideBoxPer90, 0, 5);
        gridPane.add(lblXGPerShot, 0, 6);
        gridPane.add(lblConversionRatio, 0, 7);

        gridPane.add(txfGoalsPer90, 1, 0);
        gridPane.add(txfNpxGPer90, 1, 1);
        gridPane.add(txfShotsPer90, 1, 2);
        gridPane.add(txfShotsOnTargetRatio, 1, 3);
        gridPane.add(txfShotsOnTargetPer90, 1, 4);
        gridPane.add(txfShotsOutsideBoxPer90, 1, 5);
        gridPane.add(txfXGPerShot, 1, 6);
        gridPane.add(txfConversionRatio, 1, 7);


        return gridPane;
    }

    private static void modifyComponents() {
        for (Object textField : components) {
            if (textField instanceof TextField) {
                 ((TextField) textField).setPrefWidth(40);
                ((TextField) textField).setMaxWidth(40);
                 ((TextField) textField).setStyle("-fx-alignment: CENTER;");
            }
        }

    }

    private static void addAllComponentsToList() {
        components.add(lblGoalsPer90);
        components.add(lblNpxGPer90);
        components.add(lblShotsPer90);
        components.add(lblShotsOnTargetRatio);
        components.add(lblShotsOnTargetPer90);
        components.add(lblShotsOutsideBoxPer90);
        components.add(lblXGPerShot);
        components.add(lblConversionRatio);


        components.add(txfGoalsPer90);
        components.add(txfNpxGPer90);
        components.add(txfShotsPer90);
        components.add(txfShotsOnTargetRatio);
        components.add(txfShotsOnTargetPer90);
        components.add(txfShotsOutsideBoxPer90);
        components.add(txfXGPerShot);
        components.add(txfConversionRatio);

    }

    /**
     * Apply the filters
     * @param tableView
     * @param playerAmount
     */
    public static List<Player> applyFilters(List<Player> players) {
        if (!txfGoalsPer90.getText().isEmpty())
            players = filterByGoalsPer90(players);

        if (!txfNpxGPer90.getText().isEmpty())
            players = filterByNpxGPer90(players);

        if (!txfShotsPer90.getText().isEmpty())
            players = filterByShotsPer90(players);

        if (!txfShotsOnTargetRatio.getText().isEmpty())
            players = filterByShotsOnTargetRatio(players);

        if (!txfShotsOnTargetPer90.getText().isEmpty())
            players = filterByShotsOnTargetPer90(players);

        if (!txfShotsOutsideBoxPer90.getText().isEmpty())
            players = filterByShotsOutsideBoxPer90(players);

        if (!txfXGPerShot.getText().isEmpty())
            players = filterByXGPerShot(players);

        if (!txfConversionRatio.getText().isEmpty())
            players = filterByConversionRatio(players);

        return players;
    }

    public static void resetFilters() {
        txfGoalsPer90.setText("");
        txfNpxGPer90.setText("");
        txfShotsPer90.setText("");
        txfShotsOnTargetRatio.setText("");
        txfShotsOnTargetPer90.setText("");
        txfShotsOutsideBoxPer90.setText("");
        txfXGPerShot.setText("");
        txfConversionRatio.setText("");

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

    private static List<Player> filterByShotsOnTargetRatio(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();

        for (Player player : players) {
            if (player.getShotsOnTargetRatio() >= Double.parseDouble(txfShotsOnTargetRatio.getText()))
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

    private static List<Player> filterByShotsOutsideBoxPer90(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();

        for (Player player : players) {
            if (player.getShotsOutsideBoxPer90() >= Double.parseDouble(txfShotsOutsideBoxPer90.getText()))
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

    private static List<Player> filterByConversionRatio(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();

        for (Player player : players) {
            if (player.getConversionRatio() >= Double.parseDouble(txfConversionRatio.getText()))
                filteredData.add(player);
        }

        return filteredData;
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
