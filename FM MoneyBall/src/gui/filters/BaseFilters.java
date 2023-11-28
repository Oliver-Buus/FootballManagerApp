package gui.filters;

import controller.CRUD_Controller;
import gui.AutoCompleteComboboxListener;
import gui.GuiFilters;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Player;
import model.Position;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;


public class BaseFilters {
    private static Map<String, String> map = FilterUtil.readNationalitiesFile();
    private static List<Object> components = new ArrayList<>();
    private static Label lblNationality = new Label("Nationality");
    private static Label lblDivision = new Label("Division");
    private static Label lblClub = new Label("Club");
    private static Label lblPosition = new Label("Position");
    private static Label lblAge = new Label("Age");
    private static Label lblHeight = new Label("Height");
    private static Label lblMinutes = new Label("Min. Minutes");
    private static Label lblAvgRating = new Label("Min. Avg rating");

    private static ComboBox<String> cbbNationalities = new ComboBox<>();
    private static ComboBox<String> cbbDivision = new ComboBox<>();
    private static ComboBox<String> cbbClub = new ComboBox<>();
    private static ComboBox<String> cbbPlayerPositions = new ComboBox<>();
    private static TextField txfAgeMin = new TextField();
    private static TextField txfAgeMax = new TextField();
    private static TextField txfHeightMin = new TextField();
    private static TextField txfHeightMax = new TextField();
    private static TextField txfMinutesMin = new TextField();
    private static TextField txfAvgRatingMin = new TextField();

    public static void addToGridPane(GridPane gridPane) {
        components.addAll(GuiFilters.getStaticFields(BaseFilters.class));
        GuiFilters.restrictInput(components);
        restrictInput();
        addTooltips();
        modifyComponents();

        addOptionsToPositionCombobox();
        new AutoCompleteComboboxListener<>(cbbNationalities);

        int i = 0;
        for (Object label : components) {
            if (label instanceof Label) {
                gridPane.add((Node) label, 0, i);
                i++;
            }
        }

        i = 0;
        gridPane.add(cbbNationalities, 1, i++);
        gridPane.add(cbbDivision, 1, i++);
        gridPane.add(cbbClub, 1, i++);
        gridPane.add(cbbPlayerPositions, 1, i++);
        gridPane.add(txfAgeMin, 1, i);
        gridPane.add(txfAgeMax, 1, i++);
        gridPane.add(txfHeightMin, 1, i);
        gridPane.add(txfHeightMax, 1, i++);
        gridPane.add(txfMinutesMin, 1, i++);
        gridPane.add(txfAvgRatingMin, 1, i++);
    }

    private static void modifyComponents() {
        for (Object textField : components) {
            if (textField instanceof TextField) {
                ((TextField) textField).setMaxWidth(50);
                ((TextField) textField).setStyle("-fx-alignment: CENTER;");
            }
        }

        for (Object combobox : components) {
            if (combobox instanceof ComboBox<?>) {
                ((ComboBox<?>) combobox).setMaxWidth(190);
            }
        }

        txfMinutesMin.setMaxWidth(txfMinutesMin.getWidth() + 105);
        cbbPlayerPositions.setValue("Any");
        cbbNationalities.setValue("Any");
        cbbNationalities.setEditable(true);

        txfAgeMin.setPromptText("15");
        txfAgeMax.setPromptText("45");
        txfHeightMin.setPromptText("150");
        txfHeightMax.setPromptText("200");
        txfMinutesMin.setPromptText("900");

        GridPane.setMargin(txfAgeMax, new Insets(0, 0, 0, 55));
        GridPane.setMargin(txfHeightMax, new Insets(0, 0, 0, 55));

    }

    public static List<Player> createFilters(List<Player> players) {
        GuiFilters.commaToDot(components);

        if (!cbbNationalities.getValue().equals("Any"))
            players = filterByNationality(players);

        if (!cbbDivision.getValue().equals("Any"))
            players = filterByDivision(players);

        if (!cbbClub.getValue().equals("Any"))
            players = filterByClub(players);

        if (!cbbPlayerPositions.getValue().equals("Any"))
            players = filterByPosition(players);

        if (!txfAgeMin.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "age", ">=",
                    Integer.parseInt(txfAgeMin.getText()));

        if (!txfAgeMax.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "age", "<=",
                    Integer.parseInt(txfAgeMax.getText()));

        if (!txfHeightMin.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "height", ">=",
                    Integer.parseInt(txfHeightMin.getText()));

        if (!txfHeightMax.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "height", "<=",
                    Integer.parseInt(txfHeightMax.getText()));

        if (!txfMinutesMin.getText().isEmpty())
            players = GuiFilters.filterByAttribute(players, "mins", ">=",
                    Double.parseDouble(txfMinutesMin.getText()));

        if (!txfAvgRatingMin.getText().isEmpty()) {
            players = GuiFilters.filterByAttribute(players, "avgRating", ">=",
                    Double.parseDouble(txfAvgRatingMin.getText()));
        }

        return players;
    }

    private static List<Player> filterByNationality(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();

        for (Player player : players) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue().equals(cbbNationalities.getValue())) {
                    if (player.getNationality().equals(entry.getKey()))
                        filteredData.add(player);
                }
            }
        }
        return filteredData;
    }

    private static List<Player> filterByDivision(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();

        for (Player player : players) {
            if (player.getDivision().equals(cbbDivision.getValue()))
                filteredData.add(player);
        }
        return filteredData;
    }

    private static List<Player> filterByClub(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();

        for (Player player : players) {
            if (player.getClub().equals(cbbClub.getValue()))
                filteredData.add(player);
        }
        return filteredData;
    }

    private static List<Player> filterByPosition(List<Player> players) {
        List<Player> filteredData = new ArrayList<>();

        for (Player player : players) {
            for (Position position : player.getPositions()) {
                if (position.toString().equals(cbbPlayerPositions.getValue())) {
                    filteredData.add(player);
                    break;
                }
            }
        }

        return filteredData;
    }

    private static void addOptionsToPositionCombobox() {
        cbbPlayerPositions.getItems().add("Any");
        for (Position p : Position.values()) {
            cbbPlayerPositions.getItems().add(p.toString());
        }
    }

    private static void restrictInput() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) return change;
            return null;
        };
        for (Object textField : components) {
            if (textField instanceof TextField) {
                if (!textField.equals(txfAvgRatingMin))
                ((TextField) textField).setTextFormatter(new TextFormatter<>(filter));
            }
        }
    }

    private static void addTooltips() {
        GuiFilters.addTooltips("Min. age of player", txfAgeMin);
        GuiFilters.addTooltips("Max. age of player", txfAgeMax);
        GuiFilters.addTooltips("Min. height of player in cm", txfHeightMin);
        GuiFilters.addTooltips("Max. height of player in cm", txfHeightMax);
        GuiFilters.addTooltips("Min. number of minutes played", txfMinutesMin);
    }

    public static List getComponents() {
        return components;
    }

    public static ComboBox<String> getCbbClub() {
        return cbbClub;
    }

    public static ComboBox<String> getCbbDivision() {
        return cbbDivision;
    }

    public static ComboBox<String> getCbbPlayerPositions() {
        return cbbPlayerPositions;
    }

    public static ComboBox<String> getCbbNationalities() {
        return cbbNationalities;
    }
}
