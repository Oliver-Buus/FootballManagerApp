package gui;

import controller.CRUD_Controller;
import controller.Controller;
import gui.filters.ShotFilters;
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
    private static TableView<Player> tvwPlayers = new TableView<>(); // Tabel med spillere og deres info
    private static Label lblPlayerAmount = new Label();
    private static Button btnLoadHTML = new Button("Load HTML-file");
    private static MenuBar menuBar = new MenuBar();
    private static Menu mFile = new Menu("File");
    private static MenuItem miLoad = new MenuItem("Load HTML-file");
    private static Accordion accordion = new Accordion();


    private static List<TableColumn> baseStatsList = new ArrayList();
    private static List<TableColumn> customStatsList = new ArrayList<>();
    private static List<TableColumn> shotStatsList = new ArrayList();
    private static List<TableColumn> passStatsList = new ArrayList();
    private static List<TableColumn> crossStatslist = new ArrayList<>();
    private static List<TableColumn> aerialStatslist = new ArrayList<>();
    private static List<TableColumn> movementStatslist = new ArrayList<>();
    private static List<TableColumn> tacklesStatslist = new ArrayList<>();
    private static List<TableColumn> savesStatslist = new ArrayList<>();

    //__________________________________________________________________________________________________________________

    private Scene sceneMain() throws Exception {
        BorderPane bpOuter = new BorderPane();
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.getStyleClass().add("custom-gridpane");
        borderPane.getStyleClass().add("custom-gridpane");
        bpOuter.getStyleClass().add("custom-gridpane");
        gridPane.setPrefHeight(800);
        gridPane.setPrefWidth(1600);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        scene = new Scene(bpOuter, gridPane.getPrefWidth(), gridPane.getPrefHeight());

        createTableViewColumns();
        btnLoadHTML.getStyleClass().add("custom-button");
        btnLoadHTML.setPrefSize(400, 200);

        btnLoadHTML.setOnAction(event -> {
            Controller.openFolder(stage);
            GuiFilters.resetAllFilters(tvwPlayers, lblPlayerAmount);
//            GuiFilters.resetFilters(tvwPlayers, lblPlayerAmount);
        });

        miLoad.setOnAction(event -> {
            Controller.openFolder(stage);
            GuiFilters.resetAllFilters(tvwPlayers, lblPlayerAmount);
//            GuiFilters.resetFilters(tvwPlayers, lblPlayerAmount);
        });

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) return change;
            return null;
        };

        //______________________________________________________________________________________________________________
        // ADD TO PANE
        gridPane.add(lblPlayerAmount, 0, 0);
        gridPane.add(tvwPlayers, 0, 1, 8, 1);

        //______________________________________________________________________________________________________________

        tvwPlayers.getItems().setAll(CRUD_Controller.getAllPlayers());
        lblPlayerAmount.setText("Players: " + tvwPlayers.getItems().size());
        tvwPlayers.setPrefWidth(1560);
        tvwPlayers.setPrefHeight(800);

        tvwPlayers.setPlaceholder(btnLoadHTML);
        tvwPlayers.skinProperty().addListener((obs, ol, ne) -> {
            Pane header = (Pane) tvwPlayers.lookup("TableHeaderRow");
            header.prefHeightProperty().bind(tvwPlayers.heightProperty().divide(10));
        });

        mFile.getItems().add(miLoad);
        menuBar.getMenus().add(mFile);

        //TODO ryd op i nedenstående
        accordion.getPanes().add(GuiFilters.makeFilters(tvwPlayers, lblPlayerAmount));
        accordion.setExpandedPane(accordion.getPanes().get(0));
        bpOuter.setTop(menuBar);
        bpOuter.setCenter(borderPane);

        borderPane.setTop(accordion);
        borderPane.setCenter(gridPane);

        return scene;
    }

    public static void createTableViewColumns() {

        baseStatsList = GuiTableView.addBaseStatsColumns(baseStatsList, tvwPlayers);
        customStatsList = GuiTableView.addCustomStatsColumns(customStatsList, tvwPlayers);
        shotStatsList = GuiTableView.addShotStatsColumns(shotStatsList, tvwPlayers);
        passStatsList = GuiTableView.addPassStatsColumns(passStatsList, tvwPlayers);
        crossStatslist = GuiTableView.addCrossStatsColumns(crossStatslist, tvwPlayers);
        aerialStatslist = GuiTableView.addAerialStatsColumns(aerialStatslist, tvwPlayers);
        movementStatslist= GuiTableView.addMovementStatsColumns(movementStatslist, tvwPlayers);
        tacklesStatslist = GuiTableView.addTacklesStatsColumns(tacklesStatslist, tvwPlayers);
        savesStatslist = GuiTableView.addSavesStatsColumns(savesStatslist, tvwPlayers);

        for (TableColumn shotColumn : shotStatsList) {
            applyCellStyleToColumn(shotColumn, "highlighted-cell-shots");
        }

        for (TableColumn passColumn : passStatsList) {
            applyCellStyleToColumn(passColumn, "highlighted-cell-passes");
        }

        for (TableColumn passColumn : crossStatslist) {
            applyCellStyleToColumn(passColumn, "highlighted-cell-crosses");
        }

        for (TableColumn passColumn : aerialStatslist) {
            applyCellStyleToColumn(passColumn, "highlighted-cell-aerial");
        }

        for (TableColumn movementColumn : movementStatslist) {
            applyCellStyleToColumn(movementColumn, "highlighted-cell-movement");
        }

        for (TableColumn saveColumn : tacklesStatslist) {
            applyCellStyleToColumn(saveColumn, "highlighted-cell-tackles");
        }

        for (TableColumn tackleColumn : savesStatslist) {
            applyCellStyleToColumn(tackleColumn, "highlighted-cell-saves");
        }


//        for (TableColumn tableColumn : tvwPlayers.getColumns()) {
//            tableColumn.setPrefWidth(tableColumn.getWidth() + 3);
//        }
//        tvwPlayers.setPrefHeight(480);

    }

    public static void applyCellStyleToColumn(TableColumn<?, ?> column, String styleClass) {
        column.getStyleClass().add(styleClass);
    }
}
