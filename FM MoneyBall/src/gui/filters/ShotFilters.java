package gui.filters;

import gui.GuiFilters;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public class ShotFilters {

    private static List<Object> components = new ArrayList<>();
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
        components.addAll(GuiFilters.getStaticFields(ShotFilters.class));
        GuiFilters.restrictInput(components);
        addTooltips();

        return GuiFilters.modifyGridPane(components);
    }

    public static List<Player> createFilters(List<Player> players) {
        GuiFilters.commaToDot(components);

        if (!txfGoalsPer90.getText().isEmpty())
            players =  GuiFilters.filterByAttribute(players, "glsPer90", ">=",
                    Double.parseDouble(txfGoalsPer90.getText()));

        if (!txfNpxGPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "npxGPer90", ">=",
                    Double.parseDouble(txfNpxGPer90.getText()));

        if (!txfShotsPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "shotsPer90", ">=",
                    Double.parseDouble(txfShotsPer90.getText()));

        if (!txfShotsOnTargetPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "shotsOnTargetPer90", ">=",
                    Double.parseDouble(txfShotsOnTargetPer90.getText()));

        if (!txfXGPerShot.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "xGPerShot", ">=",
                    Double.parseDouble(txfXGPerShot.getText()));

        return players;
    }

    private static void addTooltips() {
        GuiFilters.addTooltips("Goals scored per 90 minutes", txfGoalsPer90);
        GuiFilters.addTooltips("Non-penalty expected goals per 90 minutes", txfNpxGPer90);
        GuiFilters.addTooltips("Shots per 90 minutes", txfShotsPer90);
        GuiFilters.addTooltips("Shots on target per 90 minutes", txfShotsOnTargetPer90);
        GuiFilters.addTooltips("Expected goals per shot taken", txfXGPerShot);
    }

    public static List<Object> getComponents() {
        return components;
    }
}
