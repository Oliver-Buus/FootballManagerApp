package gui.filters;

import gui.GuiFilters;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public class AerialFilters {
    private static List<Object> components = new ArrayList<>();
    private static Label lblKeyHeadersWonPer90 = new Label("Key Hdrs Won/90");
    private static Label lblHeadersWonPer90 = new Label("Headers Won/90");
    private static TextField txfKeyHeadersWonPer90 = new TextField();
    private static TextField txfHeadersWonPer90 = new TextField();



    public static GridPane addToGridPane() {
        components.addAll(GuiFilters.getStaticFields(AerialFilters.class));
        GuiFilters.restrictInput(components);
        addTooltips();

        return GuiFilters.modifyGridPane(components);
    }

    public static List<Player> createFilters(List<Player> players) {
        GuiFilters.commaToDot(components);

        if (!txfKeyHeadersWonPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "keyHeadersPer90", ">=",
                    Double.parseDouble(txfKeyHeadersWonPer90.getText()));

        if (!txfHeadersWonPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "headersWonPer90", ">=",
                    Double.parseDouble(txfHeadersWonPer90.getText()));

        return players;
    }

    public static void resetFilters() {
        for (Object textField : components) {
            if (textField instanceof TextField) {
                ((TextField) textField).setText("");
            }
        }

    }

    private static void addTooltips() {
        GuiFilters.addTooltips("Key headers won per 90 minutes", txfKeyHeadersWonPer90);
        GuiFilters.addTooltips("Headers won per 90 minutes", txfHeadersWonPer90);
    }

    public static List getComponents() {
        return components;
    }
}
