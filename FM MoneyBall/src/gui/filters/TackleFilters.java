package gui.filters;

import gui.GuiFilters;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Player;
import java.util.ArrayList;
import java.util.List;


public class TackleFilters {
    private static List<Object> components = new ArrayList<>();
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
        components.addAll(GuiFilters.getStaticFields(TackleFilters.class));
        GuiFilters.restrictInput(components);
        addTooltips();

        return GuiFilters.modifyGridPane(components);
    }

    public static List<Player> createFilters(List<Player> players) {
        GuiFilters.commaToDot(components);

        if (!txfTacklesWonPer90.getText().isEmpty())
            players =  GuiFilters.filterByAttribute(players, "tacklesWonPer90", ">=",
                    Double.parseDouble(txfTacklesWonPer90.getText()));

        if (!txfShotsBlockedPer90.getText().isEmpty())
            players =  GuiFilters.filterByAttribute(players, "shotsBlockedPer90", ">=",
                    Double.parseDouble(txfShotsBlockedPer90.getText()));

        if (!txfPossessionWonPer90.getText().isEmpty())
            players =  GuiFilters.filterByAttribute(players, "possessionWonPer90", ">=",
                    Double.parseDouble(txfPossessionWonPer90.getText()));

        if (!txfKeyTacklesPer90.getText().isEmpty())
            players =  GuiFilters.filterByAttribute(players, "keyTacklesPer90", ">=",
                    Double.parseDouble(txfKeyTacklesPer90.getText()));

        if (!txfInterceptionsPer90.getText().isEmpty())
            players =  GuiFilters.filterByAttribute(players, "interceptionsPer90", ">=",
                    Double.parseDouble(txfInterceptionsPer90.getText()));

        if (!txfClearancesPer90.getText().isEmpty())
            players =  GuiFilters.filterByAttribute(players, "clearancesPer90", ">=",
                    Double.parseDouble(txfClearancesPer90.getText()));

        if (!txfBlocksPer90.getText().isEmpty())
            players =  GuiFilters.filterByAttribute(players, "blocksPer90", ">=",
                    Double.parseDouble(txfBlocksPer90.getText()));

        return players;
    }


    private static void addTooltips() {
        GuiFilters.addTooltips("Tackles won per 90 minutes", txfTacklesWonPer90);
        GuiFilters.addTooltips("Shots blocked per 90 minutes", txfShotsBlockedPer90);
        GuiFilters.addTooltips("Possession won per 90 minutes", txfPossessionWonPer90);
        GuiFilters.addTooltips("Key tackles per 90 minutes", txfKeyTacklesPer90);
        GuiFilters.addTooltips("Interceptions per 90 minutes", txfInterceptionsPer90);
        GuiFilters.addTooltips("Clearances per 90 minutes", txfClearancesPer90);
        GuiFilters.addTooltips("Blocks per 90 minutes", txfBlocksPer90);
    }

    public static List getComponents() {
        return components;
    }
}
