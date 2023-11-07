package gui;

import controller.CRUD_Controller;
import gui.filters.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Player;

import java.util.ArrayList;
import java.util.List;

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

        Accordion accordionShooting = new Accordion();
        TitledPane tpShooting = new TitledPane("Shooting stats", ShotFilters.addToGridPane());
        accordionShooting.getPanes().add(tpShooting);
        gridPane.add(accordionShooting, 2, 0, 1, 10);

        Accordion accordionPassing = new Accordion();
        TitledPane tpPassing = new TitledPane("Passing/Crossing stats", PassAndCrossFilters.addToGridPane());
        accordionPassing.getPanes().add(tpPassing);
        gridPane.add(accordionPassing, 3, 0, 1, 10);


        Accordion accordionAerial = new Accordion();
        TitledPane tpAerial = new TitledPane("Aerial stats", AerialFilters.addToGridPane());
        accordionAerial.getPanes().add(tpAerial);
        gridPane.add(accordionAerial, 4, 0, 1, 10);

        Accordion accordionMovement = new Accordion();
        TitledPane tpMovement = new TitledPane("Movement stats", MovementFilters.addToGridPane());
        accordionMovement.getPanes().add(tpMovement);
        gridPane.add(accordionMovement, 5, 0, 1, 10);

        Accordion accordionTackle = new Accordion();
        TitledPane tpTackle = new TitledPane("Tackling stats", TackleFilters.addToGridPane());
        accordionTackle.getPanes().add(tpTackle);
        gridPane.add(accordionTackle, 6, 0, 1, 10);

        Accordion accordionSave = new Accordion();
        TitledPane tpSave = new TitledPane("Saves stats", SavesFilter.addToGridPane());
        accordionSave.getPanes().add(tpSave);
        gridPane.add(accordionSave, 7, 0, 1, 10);

        gridPane.getStyleClass().add("custom-gridpane");

        gridPane.add(btnFilter, 0, 10);
        gridPane.add(btnResetFilters, 0, 10);
        GridPane.setMargin(btnResetFilters, new Insets(0, 0, 0, 55));
        gridPane.setPrefSize(1500, 150);
        gridPane.setMaxHeight(150);
    }


    private static void applyAllFilters(TableView tableView, Label playerAmount) {
        List<Player> currentPlayersList = new ArrayList<>(CRUD_Controller.getAllPlayers());

        currentPlayersList = BaseFilters.applyFilters(currentPlayersList);
        currentPlayersList = ShotFilters.applyFilters(currentPlayersList);

        tableView.getItems().setAll(currentPlayersList);
        playerAmount.setText("Players: " + tableView.getItems().size());

    }

    public static void resetAllFilters(TableView tableView, Label playerAmount) {
        tableView.getItems().setAll(CRUD_Controller.getAllPlayers());
        BaseFilters.resetFilters();
        ShotFilters.resetFilters();
        playerAmount.setText("Players: " + tableView.getItems().size());

    }
}
