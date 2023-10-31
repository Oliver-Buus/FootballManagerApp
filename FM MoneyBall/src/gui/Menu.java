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

import java.util.ArrayList;
import java.util.List;
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
        pane.setPrefHeight(720);
        pane.setPrefWidth(1280);
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
        tvwPlayers.setPrefWidth(1240);

        return scene;
    }

    public static void createTableViewColumns() {
        List<TableColumn> tcList = new ArrayList<>();

        tcList.add(new TableColumn<Player, String>("Name"));
        tcList.get(0).setCellValueFactory(new PropertyValueFactory<Player, String>("name"));

        tcList.add(new TableColumn<Player, Integer>("Age"));
        tcList.get(1).setCellValueFactory(new PropertyValueFactory<Player, Integer>("age"));

        tcList.add(new TableColumn<Player, String>("Position"));
        tcList.get(2).setCellValueFactory(new PropertyValueFactory<Player, String>("positionString"));

        tcList.add(new TableColumn<Player, String>("NAT"));
        tcList.get(3).setCellValueFactory(new PropertyValueFactory<Player, String>("nationality"));

        tcList.add(new TableColumn<Player, Integer>("Height"));
        tcList.get(4).setCellValueFactory(new PropertyValueFactory<Player, Integer>("height"));

        tcList.add(new TableColumn<Player, Integer>("Weight"));
        tcList.get(5).setCellValueFactory(new PropertyValueFactory<Player, Integer>("weight"));

        tcList.add(new TableColumn<Player, String>("Personality"));
        tcList.get(6).setCellValueFactory(new PropertyValueFactory<Player, String>("personality"));

        tcList.add(new TableColumn<Player, String>("Rc Injury"));
        tcList.get(7).setCellValueFactory(new PropertyValueFactory<Player, String>("recurringInjury"));
//        TableColumn recurringInjuryColumn = new TableColumn<Player, String>("Rc Injury");
//        recurringInjuryColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("recurringInjury"));


        for (TableColumn tc : tcList) {
            tvwPlayers.getColumns().add(tc);
            tc.setEditable(false);
        }

        tvwPlayers.setPrefHeight(480);

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
