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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Player;
import model.Position;

import java.util.*;
import java.util.List;
import java.util.function.UnaryOperator;

public class MainWindow extends Application {
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
    private static MenuBar menuBar = new MenuBar();
    private static Menu mFile = new Menu("File");
    private static MenuItem miLoad = new MenuItem("Load HTML-file");
    private static TreeView treeView = new TreeView<>();
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
        gridPane.setPrefHeight(900);
        gridPane.setPrefWidth(1600);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        scene = new Scene(bpOuter, gridPane.getPrefWidth(), gridPane.getPrefHeight());

        createTableViewColumns();

        miLoad.setOnAction(event -> {
            Controller.openFolder(stage);
            GuiFilters.resetFilters(tvwPlayers, lblPlayerAmount);
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
        tvwPlayers.skinProperty().addListener((obs, ol, ne) -> {
            Pane header = (Pane) tvwPlayers.lookup("TableHeaderRow");
            header.prefHeightProperty().bind(tvwPlayers.heightProperty().divide(10));
        });

        mFile.getItems().add(miLoad);
        menuBar.getMenus().add(mFile);

        //TODO ryd op i nedenstående
        TitledPane theOne = GuiFilters.makeFilters(tvwPlayers, lblPlayerAmount);
        accordion.getPanes().add(theOne);
        bpOuter.setTop(menuBar);
        bpOuter.setCenter(borderPane);

        borderPane.setCenter(gridPane);
        borderPane.setTop(accordion);

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


//        for (TableColumn tableColumn : tvwPlayers.getColumns()) {
//            tableColumn.setPrefWidth(tableColumn.getWidth() + 3);
//        }
//        tvwPlayers.setPrefHeight(480);

    }

    public static void applyCellStyleToColumn(TableColumn<?, ?> columnm, String styleClass) {
        columnm.getStyleClass().add(styleClass);
    }
}
