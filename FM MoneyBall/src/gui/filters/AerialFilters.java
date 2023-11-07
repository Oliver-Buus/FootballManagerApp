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

public class AerialFilters {
    private static List components = new ArrayList<>();
    private static Label lblKeyHeadersWonPer90 = new Label("Key Hdrs Won/90");
    private static Label lblHeadersWonPer90 = new Label("Headers Won/90");
    private static TextField txfKeyHeadersWonPer90 = new TextField();
    private static TextField txfHeadersWonPer90 = new TextField();



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
        components.add(lblKeyHeadersWonPer90);
        components.add(lblHeadersWonPer90);

        components.add(txfKeyHeadersWonPer90);
        components.add(txfHeadersWonPer90);
    }

    private static void addTooltips() {
        Tooltip ttKeyHeadersWonPer90 = new Tooltip("Key headers won per 90 minutes");
        txfKeyHeadersWonPer90.setTooltip(ttKeyHeadersWonPer90);
        ttKeyHeadersWonPer90.setShowDelay(Duration.millis(150));

        Tooltip ttHeadersWonPer90  =new Tooltip("Headers won per 90 minutes");
        txfHeadersWonPer90.setTooltip(ttHeadersWonPer90);
        ttHeadersWonPer90.setShowDelay(Duration.millis(150));
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
