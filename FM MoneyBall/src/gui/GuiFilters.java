package gui;

import controller.CRUD_Controller;
import gui.filters.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import model.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class GuiFilters {
    private static GridPane gridPane = new GridPane();
    private static Button btnFilter = new Button("Apply");
    private static Button btnResetFilters = new Button("Reset");


    public static TitledPane createTitledPane(TableView tableView, Label playerAmount) {
        TitledPane titledPane = new TitledPane("Filters", gridPane);

        addToGridPane();

        btnFilter.setOnAction(event -> applyAllFilters(tableView, playerAmount));
        btnResetFilters.setOnAction(event -> resetAllFilters(tableView, playerAmount));


        return titledPane;
    }

    private static void addToGridPane() {
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        BaseFilters.addToGridPane(gridPane);


        int rowSpan = 40;
        gridPane.add(addAccordion("Shooting stats",
                ShotFilters.addToGridPane()), 2, 0, 1, rowSpan);
        gridPane.add(addAccordion("Passing/Crossing stats",
                PassAndCrossFilters.addToGridPane()), 3, 0, 1, rowSpan);
        gridPane.add(addAccordion("Aerial stats",
                AerialFilters.addToGridPane()), 4, 0, 1, rowSpan);
        gridPane.add(addAccordion("Movement stats",
                MovementFilters.addToGridPane()), 5, 0, 1, rowSpan);
        gridPane.add(addAccordion("Tackling stats",
                TackleFilters.addToGridPane()), 6, 0, 1, rowSpan);
        gridPane.add(addAccordion("Saves stats",
                SavesFilter.addToGridPane()), 7, 0, 1, rowSpan);

        gridPane.getStyleClass().add("custom-gridpane");

        gridPane.add(btnFilter, 0, 10);
        gridPane.add(btnResetFilters, 0, 10);
        GridPane.setMargin(btnResetFilters, new Insets(0, 0, 0, 55));
        gridPane.setPrefSize(1500, 150);
        gridPane.setMaxHeight(150);
    }


    private static void applyAllFilters(TableView tableView, Label playerAmount) {
        List<Player> currentPlayersList = new ArrayList<>(CRUD_Controller.getAllPlayers());

        currentPlayersList = BaseFilters.createFilters(currentPlayersList);
        currentPlayersList = ShotFilters.createFilters(currentPlayersList);
        currentPlayersList = PassAndCrossFilters.createFilters(currentPlayersList);
        currentPlayersList = AerialFilters.createFilters(currentPlayersList);
        currentPlayersList = MovementFilters.createFilters(currentPlayersList);
        currentPlayersList = TackleFilters.createFilters(currentPlayersList);
        currentPlayersList = SavesFilter.createFilters(currentPlayersList);

        tableView.getItems().setAll(currentPlayersList);
        playerAmount.setText("Players: " + tableView.getItems().size());

    }

    public static GridPane modifyGridPane(List<Object> list) {
        GridPane gridPane = new GridPane();
        gridPane.getStyleClass().add("custom-gridpane");
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        Label lblMin = new Label("Min.");
        GridPane.setHalignment(lblMin, HPos.CENTER);
        gridPane.add(lblMin, 1, 0);

        int i = 1;
        for (Object label : list) {
            if (label instanceof Label) {
                ((Label) label).setPrefWidth(120);
                gridPane.add((Node) label, 0, i);
                i++;
            }
        }

        i = 1;
        for (Object textField : list) {
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


    /**
     * Empties each Textfields in each filter and updates the amount of players in the table.
     */
    public static void resetAllFilters(TableView tableView, Label playerAmount) {
        List<Object> allComponents = new ArrayList<>();
        allComponents.addAll(BaseFilters.getComponents());
        allComponents.addAll(ShotFilters.getComponents());
        allComponents.addAll(PassAndCrossFilters.getComponents());
        allComponents.addAll(AerialFilters.getComponents());
        allComponents.addAll(MovementFilters.getComponents());
        allComponents.addAll(TackleFilters.getComponents());
        allComponents.addAll(SavesFilter.getComponents());

        for (Object o : allComponents) {
            if (o instanceof TextField) {
                ((TextField) o).setText("");
            }
            if (o instanceof ComboBox<?>) {
                ((ComboBox<String>) o).setValue("Any");
            }
        }

        tableView.getItems().setAll(CRUD_Controller.getAllPlayers());
        FilterUtil.addOptionsToNationalityCombobox(CRUD_Controller.getAllPlayers());
        FilterUtil.addOptionsToDivisionCombobox(CRUD_Controller.getAllPlayers());
        FilterUtil.addOptionsToClubCombobox(CRUD_Controller.getAllPlayers());
        playerAmount.setText("Players: " + tableView.getItems().size());

    }

    /**
     * Creates filters and filters players based on an attribute. If the player's attribute is higher or lower
     * (depending on the operator) than the value, the player is added to the list.
     */
    public static List<Player> filterByAttribute(List<Player> players, String attribute,
                                                 String operator, double value) {
        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            double playerValue = getPlayerAttributeValue(player, attribute);
            if (compareValues(playerValue, operator, value))
                filteredData.add(player);
        }

        return filteredData;
    }

    private static Accordion addAccordion(String name, GridPane gridPane) {
        Accordion accordion = new Accordion();
        TitledPane titledPane = new TitledPane(name, gridPane);
        accordion.getPanes().add(titledPane);
        accordion.setExpandedPane(accordion.getPanes().get(0));
        return accordion;
    }


    /**
     * Takes a string representing an attribute and finds the corresponding getter method and returns the value of
     * that getter method.
     */
    private static double getPlayerAttributeValue(Player player, String attribute) {
        try {
            String getterMethodName = "get" + attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
            Method getterMethod = Player.class.getMethod(getterMethodName);
            if (getterMethod.getReturnType() == Integer.TYPE) return (double) (int) getterMethod.invoke(player);
            else return (double) getterMethod.invoke(player);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Couldn't get the value for attribute: " + attribute);
        }
    }

    /**
     * Identifies an operator to be used in the filter.
     */
    private static boolean compareValues(double playerValue, String operator, double filterValue) {
        return switch (operator) {
            case ">=" -> playerValue >= filterValue;
            case "<=" -> playerValue <= filterValue;
            case "="  -> playerValue == filterValue;
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        };

    }

    /**
     * Takes a textField and creates a tooltip with a tooltip message for the textfield
     */
    public static void addTooltips(String tooltipMessage, TextField textField) {
        Tooltip tooltip = new Tooltip(tooltipMessage);
        textField.setTooltip(tooltip);
        tooltip.setShowDelay(Duration.millis(150));
    }

    /**
     * Restricts the input to only be of numbers, commas and dots.
     */
    public static void restrictInput(List<Object> list) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("^[0-9.,]*$")) return change;
            return null;
        };
        for (Object textField : list) {
            if (textField instanceof TextField) {
                ((TextField) textField).setTextFormatter(new TextFormatter<>(filter));
            }
        }
    }

    /**
     * Takes a list of objects and changes any commas to dots in a textfield.
     */
    public static void commaToDot(List<Object> list) {
        for (Object textField : list) {
            if (textField instanceof TextField) {
                ((TextField) textField).setText(((TextField)
                        textField).getText().replace(",", "."));
            }
        }
    }

    /**
     * Retrieves all the static fields of a given class and returns them as a list of objects.
     */
    public static List<Object> getStaticFields(Class<?> clazz) {
        List<Object> staticFieldsList = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                try {
                    staticFieldsList.add(field.get(null)); // Her null, da det er en statisk variabel.
                } catch (IllegalAccessException e) {
                    System.out.println(Arrays.toString(e.getStackTrace()));
                }
            }
            field.setAccessible(false);
        }

        return staticFieldsList;
    }
}
