package gui.filters;

import gui.GuiFilters;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public class PassAndCrossFilters {
    private static List<Object> components = new ArrayList<>();
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
        components.addAll(GuiFilters.getStaticFields(PassAndCrossFilters.class));
        GuiFilters.restrictInput(components);
        addTooltips();

        return GuiFilters.modifyGridPane(components);
    }


    public static List<Player> createFilters(List<Player> players) {
        GuiFilters.commaToDot(components);

        if (!txfAssistsPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "assistsPer90", ">=",
                    Double.parseDouble(txfAssistsPer90.getText()));

        if (!txfXAssistsPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "xAPer90", ">=",
                    Double.parseDouble(txfXAssistsPer90.getText()));

        if (!txfProgressivePassesPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "prPassesPer90", ">=",
                    Double.parseDouble(txfProgressivePassesPer90.getText()));

        if (!txfPassesCompletedPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "passesCompletedPer90", ">=",
                    Double.parseDouble(txfPassesCompletedPer90.getText()));

        if (!txfOPKeyPassesPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "openPlayKeyPassesPer90", ">=",
                    Double.parseDouble(txfOPKeyPassesPer90.getText()));

        if (!txfKeyPassesPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "keyPassesPer90", ">=",
                    Double.parseDouble(txfKeyPassesPer90.getText()));

        if (!txfChancesCreatedPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "chancesCreatedPer90", ">=",
                    Double.parseDouble(txfChancesCreatedPer90.getText()));

        if (!txfOPCrossesCompletedPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "openPlayCrossesCompletedPer90", ">=",
                    Double.parseDouble(txfOPCrossesCompletedPer90.getText()));

        return players;
    }

    private static void addTooltips() {
        GuiFilters.addTooltips("Assists per 90 minutes", txfAssistsPer90);
        GuiFilters.addTooltips("Expected assists per 90 minutes", txfXAssistsPer90);
        GuiFilters.addTooltips("Progressive passes per 90 minutes", txfProgressivePassesPer90);
        GuiFilters.addTooltips("Passes completed per 90 minutes", txfPassesCompletedPer90);
        GuiFilters.addTooltips("Open play key passes per 90 minutes", txfOPKeyPassesPer90);
        GuiFilters.addTooltips("Key Passes per 90 minutes", txfKeyPassesPer90);
        GuiFilters.addTooltips("Chances created per 90 minutes", txfChancesCreatedPer90);
        GuiFilters.addTooltips("Open play crosses completed per 90 minutes", txfOPCrossesCompletedPer90);
    }

    public static List<Object> getComponents() {
        return components;
    }
}
