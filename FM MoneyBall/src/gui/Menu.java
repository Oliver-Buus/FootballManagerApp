package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Player;
import model.Position;
import storage.Storage;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Menu extends Application {
    private Stage stage;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("NONAMEYET");
        //stage.getIcons().add(new Image("")); // Ændrer appens ikon

        scene = sceneMain();

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    //__________________________________________________________________________________________________________________
    private static TableView<Player> tvwPlayers = new TableView<>(); // Tabel med spillere og deres info
    private static ComboBox<String> cbbPlayerPosition = new ComboBox<>(); // Combobox til at vælge positioner
    private static Label lblFilter = new Label("Filters");
    private static Label lblAge = new Label("Age");
    private static Label lblPosition = new Label("Position");
    private static Label lblHeight = new Label("Height");
    private static TextField txfAgeMin = new TextField();
    private static TextField txfAgeMax = new TextField();
    private static TextField txfHeightMin = new TextField();
    private static TextField txfHeightMax = new TextField();
    private static Button btnFilter = new Button("Filter");
    private static Button btnResetFilters = new Button("Reset");



    //__________________________________________________________________________________________________________________

    private Scene sceneMain() throws Exception {
        GridPane pane = new GridPane();
        pane.setPrefHeight(900);
        pane.setPrefWidth(1600);
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        scene = new Scene(pane,pane.getPrefWidth(), pane.getPrefHeight());

        createTableViewColumns();
        addToCbbPlayerPosition();

        btnFilter.setOnAction(event -> applyFilters());
        btnResetFilters.setOnAction(event -> resetFilters());

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
        pane.add(tvwPlayers, 0, 0, 8, 1);
        pane.add(lblFilter, 0, 1);
        pane.add(lblAge, 0, 2);
        pane.add(txfAgeMin, 1, 2);
        pane.add(txfAgeMax, 1, 2);
        GridPane.setMargin(txfAgeMax, new Insets(0, 0, 0, 55));
        pane.add(lblPosition, 0, 3);
        pane.add(cbbPlayerPosition, 1, 3, 2, 1);
        pane.add(lblHeight, 0, 4);
        pane.add(txfHeightMin, 1, 4);
        pane.add(txfHeightMax, 1, 4);
        GridPane.setMargin(txfHeightMax, new Insets(0, 0, 0, 55));
        pane.add(btnFilter,0, 10);
        pane.add(btnResetFilters, 1, 10);
        //______________________________________________________________________________________________________________

        tvwPlayers.getItems().setAll(Controller.getAllPlayers());
        tvwPlayers.setPrefWidth(1560);

        return scene;
    }

    public static void createTableViewColumns() {


        addColumn(tvwPlayers, "Division", "division", "");
        addColumn(tvwPlayers, "Club", "club", "");
        addColumn(tvwPlayers, "Name", "name", "");
        addColumn(tvwPlayers, "Age", "age", "");
        addColumn(tvwPlayers, "Position", "positionString", "");
        addColumn(tvwPlayers, "Nat", "nationality", "");
        addColumn(tvwPlayers, "2nd\nNat", "secondNationality", "");
        addColumn(tvwPlayers, "Height", "height", "");
        addColumn(tvwPlayers, "Personality", "personality", "");
        addColumn(tvwPlayers, "Rc Injury", "recurringInjury", "");
        addColumn(tvwPlayers, "EU National", "euNational", "");
        addColumn(tvwPlayers, "Home-Grown Status", "homeGrownStatus", "");
        addColumn(tvwPlayers, "Preferred Foot", "preferredFoot", "");
        GuiUtils.createWageComparator(addColumn(tvwPlayers, "Wage", "wage", ""));
        addColumn(tvwPlayers, "Expires", "contractExpiryDate", "");
        addColumn(tvwPlayers, "Transfer Status", "transferStatus", "");
        addColumn(tvwPlayers, "Transfer Value", "transferValue", "");
        addColumn(tvwPlayers, "WP Needed", "wpNeeded", "Work Permit Needed");
        addColumn(tvwPlayers, "WP Chance", "wpChance", "");

        tvwPlayers.setPrefHeight(480);


    }

    public static <S, T> TableColumn addColumn(TableView<S> tableView, String columName, String propertyName,
                                               String tooltipText) {
        Label label = new Label(columName);
        label.setWrapText(true);

        TableColumn<S, T> column = new TableColumn<>();
        column.setGraphic(label);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));

        tableView.getColumns().add(column);

        return column;
    }

    public static <S> void createWageComparator(TableColumn<S, String> wageColumn) {
        Comparator<String> wageComparator = (wage1, wage2) -> {
            int wage1Value = Integer.parseInt(wage1.replaceAll("[^0-9]", ""));
            int wage2Value = Integer.parseInt(wage2.replaceAll("[^0-9]", ""));
            return Integer.compare(wage1Value, wage2Value);
        };
        wageColumn.setComparator(wageComparator);
    }

    public static void addToCbbPlayerPosition() {
        cbbPlayerPosition.getItems().add("Any");
        for (Position p : Position.values()) {
            cbbPlayerPosition.getItems().add(p.toString());
        }
    }

    public static void applyFilters() {
        List<Player> currentPlayersList = new ArrayList<>(Controller.getAllPlayers());

        if (!txfAgeMin.getText().isEmpty() || !txfAgeMax.getText().isEmpty())
        currentPlayersList = filterByAge(currentPlayersList);

        if (!cbbPlayerPosition.getValue().equals("Any"))
        currentPlayersList = filterByPosition(currentPlayersList, cbbPlayerPosition.getValue());

        if (!txfHeightMin.getText().isEmpty() || !txfHeightMax.getText().isEmpty())
        currentPlayersList = filterByHeight(currentPlayersList);


        // TODO Filters for nationality and more
        //


        tvwPlayers.getItems().setAll(currentPlayersList);
    }

    public static void resetFilters() {
        tvwPlayers.getItems().setAll(Controller.getAllPlayers());
        cbbPlayerPosition.setValue("Any");
        txfHeightMin.setText("");
        txfHeightMax.setText("");
        txfAgeMin.setText("");
        txfAgeMax.setText("");
    }

    public static List<Player> filterByAge(List<Player> players) {
        if (txfAgeMin.getText().isEmpty()) txfAgeMin.setText("0");
        else if (txfAgeMax.getText().isEmpty()) txfAgeMax.setText("100");

        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if (player.getAge() >= Integer.parseInt(txfAgeMin.getText()) &&
                    player.getAge() <= Integer.parseInt(txfAgeMax.getText())) {
                filteredData.add(player);
            }
        }

        return filteredData;
    }
    public static List<Player> filterByPosition(List<Player> players, String selectedPosition) {
        List<Player> filteredData = new ArrayList<>();

        for (Player player : players) {
            for (Position position : player.getPositions()) {
                if (position.toString().equals(selectedPosition)) {
                    filteredData.add(player);
                    break;
                }
            }
        }

        return filteredData;
    }
    public static List<Player> filterByHeight(List<Player> players) {
        if (txfHeightMin.getText().isEmpty()) txfHeightMin.setText("100");
        else if (txfHeightMax.getText().isEmpty()) txfHeightMax.setText("250");

        List<Player> filteredData = new ArrayList<>();
        for (Player player : players) {
            if (player.getHeight() >= Integer.parseInt(txfHeightMin.getText()) &&
                    player.getHeight() <= Integer.parseInt(txfHeightMax.getText())) {
                filteredData.add(player);
            }
        }

        return filteredData;
    }


}
