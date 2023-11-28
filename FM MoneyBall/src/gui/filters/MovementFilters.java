package gui.filters;

import gui.GuiFilters;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Player;
import java.util.ArrayList;
import java.util.List;

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
        components.addAll(GuiFilters.getStaticFields(MovementFilters.class));
        GuiFilters.restrictInput(components);
        addTooltips();

        return GuiFilters.modifyGridPane(components);
    }

    public static List<Player> createFilters(List<Player> players) {
        GuiFilters.commaToDot(components);

        if (!txfPressuresCompletedPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "pressuresCompletedPer90", ">=",
                    Double.parseDouble(txfPressuresCompletedPer90.getText()));

        if (!txfPressuresAttemptedPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "pressuresAttemptedPer90", ">=",
                    Double.parseDouble(txfPressuresAttemptedPer90.getText()));

        if (!txfHighIntensitySprintsPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "sprintsPer90", ">=",
                    Double.parseDouble(txfHighIntensitySprintsPer90.getText()));

        if (!txfDribblesPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "dribblesPer90", ">=",
                    Double.parseDouble(txfDribblesPer90.getText()));

        if (!txfDistanceCoveredPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "distanceCoveredPer90", ">=",
                    Double.parseDouble(txfDistanceCoveredPer90.getText()));

        if (!txfPossessionLostPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "possessionLostPer90", "<=",
                    Double.parseDouble(txfPossessionLostPer90.getText()));

        return players;
    }


    private static void addTooltips() {
        GuiFilters.addTooltips("Pressures completed per 90 minutes", txfPressuresCompletedPer90);
        GuiFilters.addTooltips("Pressures attempted per 90 minutes", txfPressuresAttemptedPer90);
        GuiFilters.addTooltips("High intensity sprints per 90 minutes", txfHighIntensitySprintsPer90);
        GuiFilters.addTooltips("Dribbles made per 90 minutes", txfDribblesPer90);
        GuiFilters.addTooltips("Distance covered per 90 minutes", txfDistanceCoveredPer90);
        GuiFilters.addTooltips("Possession lost per 90 minutes", txfPossessionLostPer90);
    }

    public static List getComponents() {
        return components;
    }
}
