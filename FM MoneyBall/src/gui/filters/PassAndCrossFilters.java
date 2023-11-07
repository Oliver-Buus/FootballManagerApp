package gui.filters;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class PassAndCrossFilters {
    private static List components = new ArrayList<>();
    private static Label lblAssistsPer90 = new Label("Assists/90");
    private static Label lblXAssistsPer90 = new Label("xAssists/90");
    private static Label lblProgressivePassesPer90 = new Label("Pr. Passes/90");
    private static Label lblPassesCompletedPer90 = new Label("Passes Compl./90");
    private static Label lblOPKeyPassesPer90 = new Label("OP-Key Passes/90");
    private static Label lblKeyPassesPer90 = new Label("Key Passes/90");
    private static Label lblChancesCreatedPer90 = new Label("Chances Created/90");
    private static Label lblOPCrossesCompletedPer90 = new Label("OP-Crs Compl./90");
    private static TextField txfAssistsPer90 = new TextField();
    private static TextField txfXAssistsPer90 = new TextField();
    private static TextField txfProgressivePassesPer90 = new TextField();
    private static TextField txfPassesCompletedPer90 = new TextField();
    private static TextField txfOPKeyPassesPer90 = new TextField();
    private static TextField txfKeyPassesPer90 = new TextField();
    private static TextField txfChancesCreatedPer90 = new TextField();
    private static TextField txfOPCrossesCompletedPer90 = new TextField();

    public static GridPane addToGridPane() {
        GridPane gridPane = new GridPane();
        addAllComponentsToList();
//        modifyComponents();
        restrictInput();
        addTooltips();

        gridPane.getStyleClass().add("custom-gridpane");
        gridPane.setVgap(5);
        gridPane.setHgap(5);

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
        Label lblMin = new Label("Min.");
        GridPane.setHalignment(lblMin, HPos.CENTER);
        gridPane.add(lblMin, 1, 0);

        return gridPane;
    }

    private static void addTooltips() {
        Tooltip ttAssistsPer90 = new Tooltip("Assists per 90 minutes");
        txfAssistsPer90.setTooltip(ttAssistsPer90);
        ttAssistsPer90.setShowDelay(Duration.millis(150));

        Tooltip ttXAssistsPer90 = new Tooltip("Expected assists per 90 minutes");
        txfXAssistsPer90.setTooltip(ttXAssistsPer90);
        ttXAssistsPer90.setShowDelay(Duration.millis(150));

        Tooltip ttPrPassesPer90 = new Tooltip("Progressive passes per 90 minutes");
        txfProgressivePassesPer90.setTooltip(ttPrPassesPer90);
        ttPrPassesPer90.setShowDelay(Duration.millis(150));

        Tooltip ttPassesCompletedPer90 = new Tooltip("Passes completed per 90 minutes");
        txfPassesCompletedPer90.setTooltip(ttPassesCompletedPer90);
        ttPassesCompletedPer90.setShowDelay(Duration.millis(150));

        Tooltip ttOPKeyPassesPer90 = new Tooltip("Open Play Key Passes per 90 minutes");
        txfOPKeyPassesPer90.setTooltip(ttOPKeyPassesPer90);
        ttOPKeyPassesPer90.setShowDelay(Duration.millis(150));

        Tooltip ttKeyPassesPer90 = new Tooltip("Key Passes per 90 minutes");
        txfKeyPassesPer90.setTooltip(ttKeyPassesPer90);
        ttKeyPassesPer90.setShowDelay(Duration.millis(150));

        Tooltip ttChancesCreatedPer90 = new Tooltip("Chances Created per 90 minutes");
        txfChancesCreatedPer90.setTooltip(ttChancesCreatedPer90);
        ttChancesCreatedPer90.setShowDelay(Duration.millis(150));

        Tooltip ttOPCrossesCompletedPer90 = new Tooltip("Open Play Crosses Completed per 90 minutes");
        txfOPCrossesCompletedPer90.setTooltip(ttOPCrossesCompletedPer90);
        ttOPCrossesCompletedPer90.setShowDelay(Duration.millis(150));
    }


    private static void addAllComponentsToList() {
        components.add(lblAssistsPer90);
        components.add(lblXAssistsPer90);
        components.add(lblProgressivePassesPer90);
        components.add(lblPassesCompletedPer90);
        components.add(lblOPKeyPassesPer90);
        components.add(lblKeyPassesPer90);
        components.add(lblChancesCreatedPer90);
        components.add(lblOPCrossesCompletedPer90);

        components.add(txfAssistsPer90);
        components.add(txfXAssistsPer90);
        components.add(txfProgressivePassesPer90);
        components.add(txfPassesCompletedPer90);
        components.add(txfOPKeyPassesPer90);
        components.add(txfKeyPassesPer90);
        components.add(txfChancesCreatedPer90);
        components.add(txfOPCrossesCompletedPer90);


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
