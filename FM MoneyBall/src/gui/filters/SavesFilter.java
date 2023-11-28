package gui.filters;

import gui.GuiFilters;
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
    private static List<Object> components = new ArrayList<>();
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
        components.addAll(GuiFilters.getStaticFields(SavesFilter.class));
        GuiFilters.restrictInput(components);
        addTooltips();

        return GuiFilters.modifyGridPane(components);
    }



    public static List<Player> createFilters(List<Player> players) {
        GuiFilters.commaToDot(components);

        if (!txfSavesPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "savesPer90", ">=",
                    Double.parseDouble(txfSavesPer90.getText()));

        if (!txfConcededPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "concededPer90", ">=",
                    Double.parseDouble(txfConcededPer90.getText()));

        if (!txfCleanSheetsPer90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "cleanSheetsPer90", ">=",
                    Double.parseDouble(txfCleanSheetsPer90.getText()));

        if (!txfSaveRatio.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "saveRatio", ">=",
                    Double.parseDouble(txfSaveRatio.getText()));

        if (!txfXSaveRatio.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "xsaveRatio", ">=",
                    Double.parseDouble(txfXSaveRatio.getText()));

        if (!txfXGoalsPreventedper90.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "xGoalsPreventedPer90", ">=",
                    Double.parseDouble(txfXGoalsPreventedper90.getText()));

        return players;
    }

    private static void addTooltips() {
        GuiFilters.addTooltips("Saves per 90 minutes", txfSavesPer90);
        GuiFilters.addTooltips("Goals conceded per 90 minutes", txfConcededPer90);
        GuiFilters.addTooltips("Clean sheets per 90 minutes", txfCleanSheetsPer90);
        GuiFilters.addTooltips("Save percentage", txfSaveRatio);
        GuiFilters.addTooltips("Expected save percentage", txfXSaveRatio);
        GuiFilters.addTooltips("Expected goals prevented per 90 minutes", txfXGoalsPreventedper90);
    }

    public static List<Object> getComponents() {
        return components;
    }
}
