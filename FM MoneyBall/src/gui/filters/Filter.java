package gui.filters;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

public interface Filter {

    /**
     * Add the Components to the gridpane
     */
    public static void addToGridPane(GridPane gridPane) {

    }

    /**
     * Make the necessary changes to the components
     */
    private static void modifyComponents() {

    }

    /**
     * Add all the components to an arraylist
     */
    private static void addAllComponentsToList() {

    }

    /**
     * Apply the filters
     * @param tableView
     * @param playerAmount
     */
    public static void applyFilters(TableView tableView, Label playerAmount) {

    }
}
