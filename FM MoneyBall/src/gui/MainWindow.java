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
    private static ComboBox<String> cbbPlayerPosition = new ComboBox<>(); // Combobox til at vælge positioner
    private static Label lblFilter = new Label("Filters");
    private static Label lblPlayerAmount = new Label();
    private static Label lblAge = new Label("Age");
    private static Label lblPosition = new Label("Position");
    private static Label lblHeight = new Label("Height");
    private static TextField txfAgeMin = new TextField();
    private static TextField txfAgeMax = new TextField();
    private static TextField txfHeightMin = new TextField();
    private static TextField txfHeightMax = new TextField();
    private static Button btnFilter = new Button("Filter");
    private static Button btnResetFilters = new Button("Reset");
    private static MenuBar menuBar = new MenuBar();
    private static Menu mFile = new Menu("File");
    private static MenuItem miLoad = new MenuItem("Load HTML-file");


    private static List<TableColumn> baseStatsList = new ArrayList();
    private static List<TableColumn> shotStatsList = new ArrayList();
    private static List<TableColumn> passStatsList = new ArrayList();
    private static List<TableColumn> crossStatslist = new ArrayList<>();
    private static List<TableColumn> aerialStatslist = new ArrayList<>();
    private static List<TableColumn> movementStatslist = new ArrayList<>();
    private static List<TableColumn> tacklesStatslist = new ArrayList<>();
    private static List<TableColumn> savesStatslist = new ArrayList<>();

    //__________________________________________________________________________________________________________________

    private Scene sceneMain() throws Exception {
        BorderPane borderPane = new BorderPane();
        GridPane pane = new GridPane();
        pane.getStyleClass().add("custom-gridpane");
        pane.setPrefHeight(900);
        pane.setPrefWidth(1600);
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        scene = new Scene(borderPane);
//        scene = new Scene(pane,pane.getPrefWidth(), pane.getPrefHeight());

        createTableViewColumns();
        addToCbbPlayerPosition();

        btnFilter.setOnAction(event -> applyFilters());
        btnResetFilters.setOnAction(event -> resetFilters());
        miLoad.setOnAction(event -> {
            Controller.openFolder(stage);
            resetFilters();
        });

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) return change;
            return null;
        };
        txfAgeMin.setTextFormatter(new TextFormatter<>(filter));
        txfAgeMax.setTextFormatter(new TextFormatter<>(filter));
        txfHeightMin.setTextFormatter(new TextFormatter<>(filter));
        txfHeightMax.setTextFormatter(new TextFormatter<>(filter));



        txfAgeMin.setPromptText("15");
        txfAgeMin.setMaxWidth(35);
        txfAgeMin.setStyle("-fx-alignment: CENTER;");
        txfAgeMax.setPromptText("45");
        txfAgeMax.setMaxWidth(35);
        txfAgeMax.setStyle("-fx-alignment: CENTER;");
        cbbPlayerPosition.setPromptText("Position");
        cbbPlayerPosition.setValue("Any");
        txfHeightMin.setPromptText("100");
        txfHeightMin.setMaxWidth(35);
        txfHeightMin.setStyle("-fx-alignment: CENTER;");
        txfHeightMax.setPromptText("250");
        txfHeightMax.setMaxWidth(35);
        txfHeightMax.setStyle("-fx-alignment: CENTER;");

        //______________________________________________________________________________________________________________
        // ADD TO PANE
        pane.add(lblPlayerAmount, 0, 0);
        pane.add(tvwPlayers, 0, 1, 8, 1);
        pane.add(lblFilter, 0, 2);
        pane.add(lblAge, 0, 3);
        pane.add(txfAgeMin, 1, 3);
        pane.add(txfAgeMax, 1, 3);
        GridPane.setMargin(txfAgeMax, new Insets(0, 0, 0, 55));
        pane.add(lblPosition, 0, 4);
        pane.add(cbbPlayerPosition, 1, 4, 2, 1);
        pane.add(lblHeight, 0, 5);
        pane.add(txfHeightMin, 1, 5);
        pane.add(txfHeightMax, 1, 5);
        GridPane.setMargin(txfHeightMax, new Insets(0, 0, 0, 55));
        pane.add(btnFilter,0, 10);
        pane.add(btnResetFilters, 1, 10);
        //______________________________________________________________________________________________________________

        tvwPlayers.getItems().setAll(CRUD_Controller.getAllPlayers());
        lblPlayerAmount.setText("Players: " + tvwPlayers.getItems().size());
        tvwPlayers.setPrefWidth(1560);
        tvwPlayers.skinProperty().addListener((obs, ol, ne) -> {
            Pane header = (Pane) tvwPlayers.lookup("TableHeaderRow");
            header.prefHeightProperty().bind(tvwPlayers.heightProperty().divide(8));
        });

        mFile.getItems().add(miLoad);
        menuBar.getMenus().add(mFile);
        borderPane.setCenter(pane);
        borderPane.setTop(menuBar);

        return scene;
    }

    public static void createTableViewColumns() {

        baseStatsList = GuiTableView.addBaseStatsColumns(baseStatsList, tvwPlayers);
        shotStatsList = GuiTableView.addShotStatsColumns(shotStatsList, tvwPlayers);
        passStatsList = GuiTableView.addPassStatsColumns(passStatsList, tvwPlayers);
        crossStatslist = GuiTableView.addCrossStatsColumns(crossStatslist, tvwPlayers);
        aerialStatslist = GuiTableView.addAerialStatsColumns(aerialStatslist, tvwPlayers);
        movementStatslist= GuiTableView.addMovementStatsColumns(movementStatslist, tvwPlayers);
        tacklesStatslist = GuiTableView.addTacklesStatsColumns(tacklesStatslist, tvwPlayers);
        savesStatslist = GuiTableView.addSavesStatsColumns(savesStatslist, tvwPlayers);

//        // Player
//        baseStatsList.add(addColumn(tvwPlayers, "Club", "club"));
//        baseStatsList.add(addColumn(tvwPlayers, "Name", "name"));
//        baseStatsList.add(addColumn(tvwPlayers, "Age", "age"));
//        baseStatsList.add(addColumn(tvwPlayers, "Positions", "positionString"));
//        baseStatsList.add(addColumn(tvwPlayers, "Nationality", "nationality"));
//        baseStatsList.add(addColumn(tvwPlayers, "Height", "height"));
//        baseStatsList.add(addColumn(tvwPlayers, "Personality", "personality"));
//        baseStatsList.add(addColumn(tvwPlayers, "Recurring Injury", "recurringInjury"));
//        baseStatsList.add(addColumn(tvwPlayers, "Preferred Foot", "preferredFoot"));
//        TableColumn wageColumn = addColumn(tvwPlayers, "Wage", "wage");
//        GuiUtils.createWageComparator(wageColumn);
//        baseStatsList.add(wageColumn);
//        baseStatsList.add(addColumn(tvwPlayers, "Contract Expiry Date", "contractExpiryDate"));
//        baseStatsList.add(addColumn(tvwPlayers, "Transfer Value", "transferValue"));
//
//        // PP2Universal
//        TableColumn appsColumn = addColumn(tvwPlayers, "Appearences", "apps");
//        GuiUtils.createAppsComparator(appsColumn);
//        baseStatsList.add(appsColumn);
//        baseStatsList.add(addColumn(tvwPlayers, "Minutes Played", "mins"));
//        baseStatsList.add(addColumn(tvwPlayers, "Minutes/Game", "minsPerGame"));
//        baseStatsList.add(addColumn(tvwPlayers, "Average Rating", "avgRating"));
//
//        // PP3CustomStats
//
//
//        // PP4Shots
//        shotStatsList.add(addColumn(tvwPlayers, "Goals", "goals"));
//        shotStatsList.add(addColumn(tvwPlayers, "Goals/90", "glsPer90"));
//        shotStatsList.add(addColumn(tvwPlayers, "Non-Penalty xG", "npxG"));
//        shotStatsList.add(addColumn(tvwPlayers, "Non-Penalty xG/90", "npxGPer90"));
//        shotStatsList.add(addColumn(tvwPlayers, "Minutes/Goal", "minsPerGoal"));
//        shotStatsList.add(addColumn(tvwPlayers, "Shots/90", "shotsPer90"));
//        shotStatsList.add(addColumn(tvwPlayers, "Shots on Target %", "shotsOnTargetRatio"));
//        shotStatsList.add(addColumn(tvwPlayers, "Shots on Target/90", "shotsOnTargetPer90"));
//        shotStatsList.add(addColumn(tvwPlayers, "Shots Outside Box/90", "shotsOutsideBoxPer90"));
//        shotStatsList.add(addColumn(tvwPlayers, "Goals Outside Box", "goalsOutsideBox"));
//        shotStatsList.add(addColumn(tvwPlayers, "xG/Shot", "xGPerShot"));
//        shotStatsList.add(addColumn(tvwPlayers, "Goals/Shot", "conversionRatio"));
//
//        // PP5Passes
//        passStatsList.add(addColumn(tvwPlayers, "Assists", "assists"));
//        passStatsList.add(addColumn(tvwPlayers, "Assists/90", "assistsPer90"));
//        passStatsList.add(addColumn(tvwPlayers, "Expected Assists", "xA"));
//        passStatsList.add(addColumn(tvwPlayers, "Expected Assists/90", "xAPer90"));
//        passStatsList.add(addColumn(tvwPlayers, "Progressive Passes/90", "prPassesPer90"));
//        passStatsList.add(addColumn(tvwPlayers, "Passes Completed/90", "passesCompletedPer90"));
//        passStatsList.add(addColumn(tvwPlayers, "Pass Completion %", "passCompletionRatio"));
//        passStatsList.add(addColumn(tvwPlayers, "Open Play Key Passes/90", "openPlayKeyPassesPer90"));
//        passStatsList.add(addColumn(tvwPlayers, "Key Passes Per 90", "keyPassesPer90"));
//        passStatsList.add(addColumn(tvwPlayers, "Chances Created/90", "chancesCreatedPer90"));

        for (TableColumn shotColumn : shotStatsList) {
            applyCellStyleToColumn(shotColumn, "highlighted-cell-shots");
        }

        for (TableColumn passColumn : passStatsList) {
            applyCellStyleToColumn(passColumn, "highlighted-cell-passes");
        }

        for (TableColumn tableColumn : tvwPlayers.getColumns()) {
            tableColumn.setPrefWidth(tableColumn.getWidth() + 3);
        }
        tvwPlayers.setPrefHeight(480);

    }

    public static <S, T> TableColumn addColumn(TableView<S> tableView, String columName, String propertyName) {
        Label label = new Label(columName);
        label.setWrapText(true);

        TableColumn<S, T> column = new TableColumn<>();
        column.setGraphic(label);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));

        tableView.getColumns().add(column);

        return column;
    }

    /**
     * Metoden tager værdien af være Position i enumeratoren Position og tilføjer dem til en combobox
     */
    public static void addToCbbPlayerPosition() {
        cbbPlayerPosition.getItems().add("Any");
        for (Position p : Position.values()) {
            cbbPlayerPosition.getItems().add(p.toString());
        }
    }

    public static void applyFilters() {
        List<Player> currentPlayersList = new ArrayList<>(CRUD_Controller.getAllPlayers());

        if (!txfAgeMin.getText().isEmpty() || !txfAgeMax.getText().isEmpty())
        currentPlayersList = GuiFilters.filterByAge(currentPlayersList, txfAgeMin, txfAgeMax);

        if (!cbbPlayerPosition.getValue().equals("Any"))
        currentPlayersList = GuiFilters.filterByPosition(currentPlayersList, cbbPlayerPosition.getValue());

        if (!txfHeightMin.getText().isEmpty() || !txfHeightMax.getText().isEmpty())
        currentPlayersList = GuiFilters.filterByHeight(currentPlayersList, txfHeightMin, txfHeightMax);



        // TODO Filters for nationality and more

        tvwPlayers.getItems().setAll(currentPlayersList);
        lblPlayerAmount.setText("Players: " + tvwPlayers.getItems().size());

    }

    public static void resetFilters() {
        tvwPlayers.getItems().setAll(CRUD_Controller.getAllPlayers());
        cbbPlayerPosition.setValue("Any");
        txfHeightMin.setText("");
        txfHeightMax.setText("");
        txfAgeMin.setText("");
        txfAgeMax.setText("");
        lblPlayerAmount.setText("Players: " + tvwPlayers.getItems().size());
    }

    public static void applyCellStyleToColumn(TableColumn<?, ?> columnm, String styleClass) {
        columnm.getStyleClass().add(styleClass);
    }


}
