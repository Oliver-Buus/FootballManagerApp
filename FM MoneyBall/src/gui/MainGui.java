package gui;

import controller.CRUD_Controller;
import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Player;

import java.util.*;
import java.util.List;
import java.util.function.UnaryOperator;

public class MainGui extends Application {
    private Stage stage;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("NONAMEYET");
        //stage.getIcons().add(new Image("")); // Ændrer appens ikon
        String css = this.getClass().getResource("/css/Menu.css").toExternalForm();

        scene = sceneMain();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    //__________________________________________________________________________________________________________________
    private static TableView<Player> tvwPlayers = new TableView<>();        // Table with players and stats
    private static Label lblPlayerAmount = new Label();                     // Amount of players in table
    private static Button btnBigLoadHTML = new Button("Load HTML-file"); // Big button for initial loading of HTML
    private static Button btnLoadHTML = new Button("Load HTML");         // Button for loading of HTML


    //__________________________________________________________________________________________________________________

    private Scene sceneMain() throws Exception {
        // Call of other methods
        createTableViewColumns();
        tableViewModification();

        // Creation
        BorderPane borderPane = new BorderPane();   // Outer pane
        GridPane gridPane = new GridPane();         // Pane used for table
        Accordion accordion = new Accordion();      // Accordion used for filtering

        // Setup
        scene = new Scene(borderPane, 1600, 800);
        lblPlayerAmount.setText("Players: " + tvwPlayers.getItems().size());

        // Add to BorderPane
        borderPane.setCenter(btnBigLoadHTML);

        // Add to GridPane
        gridPane.add(lblPlayerAmount, 0, 0);
        gridPane.add(tvwPlayers, 0, 1, 8, 1);
        gridPane.add(btnLoadHTML, 1, 0);


        // Add to Accordion
        accordion.getPanes().add(GuiFilters.createTitledPane(tvwPlayers, lblPlayerAmount));


        // BorderPane Modification
        borderPane.getStyleClass().add("custom-gridpane");

        // GridPane Modification
        gridPane.getStyleClass().add("custom-gridpane");
        gridPane.setPrefHeight(800);
        gridPane.setPrefWidth(1600);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Accordion Modification
        accordion.setExpandedPane(accordion.getPanes().get(0));


        // Buttons methods
        btnBigLoadHTML.getStyleClass().add("custom-button");
        btnBigLoadHTML.setPrefSize(400, 200);

        btnBigLoadHTML.setOnAction(event -> {
            if (Controller.openFolder(stage)) {
                GuiFilters.resetAllFilters(tvwPlayers, lblPlayerAmount);
                borderPane.setTop(accordion);
                borderPane.setCenter(gridPane);
            }
        });

        btnLoadHTML.setOnAction(event -> {
            if (Controller.openFolder(stage)) {
                GuiFilters.resetAllFilters(tvwPlayers, lblPlayerAmount);
            }
        });

        return scene;
    }

    private static void tableViewModification() {
        tvwPlayers.getItems().setAll(CRUD_Controller.getAllPlayers());
        tvwPlayers.setPrefWidth(1560);
        tvwPlayers.setPrefHeight(800);
        tvwPlayers.setPlaceholder(btnBigLoadHTML);

        // Lambda-expression to change the height of the header in the table
        tvwPlayers.skinProperty().addListener((obs, ol, ne) -> {
            Pane header = (Pane) tvwPlayers.lookup("TableHeaderRow");
            header.prefHeightProperty().bind(tvwPlayers.heightProperty().divide(10));
        });
    }



    private static void createTableViewColumns() {
        List<TableColumn> baseStatsList = new ArrayList();
        List<TableColumn> customStatsList = new ArrayList<>();
        List<TableColumn> shotStatsList = new ArrayList();
        List<TableColumn> passStatsList = new ArrayList();
        List<TableColumn> crossStatslist = new ArrayList<>();
        List<TableColumn> aerialStatslist = new ArrayList<>();
        List<TableColumn> movementStatslist = new ArrayList<>();
        List<TableColumn> tacklesStatslist = new ArrayList<>();
        List<TableColumn> savesStatslist = new ArrayList<>();


        baseStatsList = GuiTableView.addBaseStatsColumns(baseStatsList, tvwPlayers);
        customStatsList = GuiTableView.addCustomStatsColumns(customStatsList, tvwPlayers);
        shotStatsList = GuiTableView.addShotStatsColumns(shotStatsList, tvwPlayers);
        passStatsList = GuiTableView.addPassStatsColumns(passStatsList, tvwPlayers);
        crossStatslist = GuiTableView.addCrossStatsColumns(crossStatslist, tvwPlayers);
        aerialStatslist = GuiTableView.addAerialStatsColumns(aerialStatslist, tvwPlayers);
        movementStatslist= GuiTableView.addMovementStatsColumns(movementStatslist, tvwPlayers);
        tacklesStatslist = GuiTableView.addTacklesStatsColumns(tacklesStatslist, tvwPlayers);
        savesStatslist = GuiTableView.addSavesStatsColumns(savesStatslist, tvwPlayers);

        for (TableColumn basestat : baseStatsList) {
            basestat.setMinWidth(65);
        }

        for (TableColumn shotColumn : shotStatsList) {
            applyCellStyleToColumn(shotColumn, "highlighted-cell-shots");
            shotColumn.setPrefWidth(65);
        }

        for (TableColumn customColumn : customStatsList) {
            customColumn.setPrefWidth(65);
        }

        for (TableColumn passColumn : passStatsList) {
            applyCellStyleToColumn(passColumn, "highlighted-cell-passes");
            passColumn.setPrefWidth(65);
        }

        for (TableColumn crossColumn : crossStatslist) {
            applyCellStyleToColumn(crossColumn, "highlighted-cell-crosses");
            crossColumn.setPrefWidth(65);
        }

        for (TableColumn aerialColumn : aerialStatslist) {
            applyCellStyleToColumn(aerialColumn, "highlighted-cell-aerial");
            aerialColumn.setPrefWidth(65);
        }

        for (TableColumn movementColumn : movementStatslist) {
            applyCellStyleToColumn(movementColumn, "highlighted-cell-movement");
            movementColumn.setPrefWidth(65);
        }

        for (TableColumn tackleColumn : tacklesStatslist) {
            applyCellStyleToColumn(tackleColumn, "highlighted-cell-tackles");
            tackleColumn.setPrefWidth(65);
        }

        for (TableColumn saveColumn : savesStatslist) {
            applyCellStyleToColumn(saveColumn, "highlighted-cell-saves");
            saveColumn.setPrefWidth(65);
        }


//        for (TableColumn tableColumn : tvwPlayers.getColumns()) {
//            tableColumn.setPrefWidth(tableColumn.getWidth() + 3);
//        }
//        tvwPlayers.setPrefHeight(480);

    }

    private static void applyCellStyleToColumn(TableColumn<?, ?> column, String styleClass) {
        column.getStyleClass().add(styleClass);
    }
}
