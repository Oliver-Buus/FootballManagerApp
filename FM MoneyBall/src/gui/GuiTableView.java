package gui;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class GuiTableView {

    public static List<TableColumn> addBaseStatsColumns(List<TableColumn> list, TableView tableView) {
        list.add(addColumn(tableView, "Club", "club"));
        list.add(addColumn(tableView, "Name", "name"));
        list.add(addColumn(tableView, "Age", "age"));
        list.add(addColumn(tableView, "Positions", "positionString"));
        list.add(addColumn(tableView, "Nationality", "nationality"));
        list.add(addColumn(tableView, "Height", "height"));
        list.add(addColumn(tableView, "Personality", "personality"));
        list.add(addColumn(tableView, "Recurring Injury", "recurringInjury"));
        list.add(addColumn(tableView, "Preferred Foot", "preferredFoot"));
        TableColumn wageColumn = addColumn(tableView, "Wage", "wage");
        GuiUtils.createWageComparator(wageColumn);
        list.add(wageColumn);
        list.add(addColumn(tableView, "Contract Expiry Date", "contractExpiryDate"));
        list.add(addColumn(tableView, "Transfer Value", "transferValue"));

        TableColumn appsColumn = addColumn(tableView, "Appearences", "apps");
        GuiUtils.createAppsComparator(appsColumn);
        list.add(appsColumn);
        list.add(addColumn(tableView, "Minutes Played", "mins"));
        list.add(addColumn(tableView, "Minutes/Game", "minsPerGame"));
        list.add(addColumn(tableView, "Average Rating", "avgRating"));

        return list;
    }

    public static List<TableColumn> addCustomStatsColumns(List<TableColumn> list, TableView tableView) {
        list.add(addColumn(tableView, "NPxG Over-Performance", "npxGOP"));
        list.add(addColumn(tableView, "Offsides/90", "offsidesPer90"));

        return list;
    }


    public static List<TableColumn> addShotStatsColumns(List<TableColumn> list, TableView tableView) {
        list.add(addColumn(tableView, "Goals", "goals"));
        list.add(addColumn(tableView, "Non-Penalty xG", "npxG"));
        list.add(addColumn(tableView, "Goals/90", "glsPer90"));
        list.add(addColumn(tableView, "Non-Penalty xG/90", "npxGPer90"));
        list.add(addColumn(tableView, "Shots/90", "shotsPer90"));
        list.add(addColumn(tableView, "Shots on Target/90", "shotsOnTargetPer90"));
        list.add(addColumn(tableView, "Shots on Target %", "shotsOnTargetRatio"));
        list.add(addColumn(tableView, "xG/Shot", "xGPerShot"));
        list.add(addColumn(tableView, "Minutes/Goal", "minsPerGoal"));
//        list.add(addColumn(tableView, "Shots Outside Box/90", "shotsOutsideBoxPer90"));
//        list.add(addColumn(tableView, "Goals Outside Box", "goalsOutsideBox"));
//        list.add(addColumn(tableView, "Conversion %", "conversionRatio"));

        return list;
    }

    public static List<TableColumn> addPassStatsColumns(List<TableColumn> list, TableView tableView) {
        list.add(addColumn(tableView, "Assists", "assists"));
        list.add(addColumn(tableView, "Assists/90", "assistsPer90"));
        list.add(addColumn(tableView, "Expected Assists", "xA"));
        list.add(addColumn(tableView, "Expected Assists/90", "xAPer90"));
        list.add(addColumn(tableView, "Progressive Passes/90", "prPassesPer90"));
        list.add(addColumn(tableView, "Passes Completed/90", "passesCompletedPer90"));
        list.add(addColumn(tableView, "Pass Completion %", "passCompletionRatio"));
        list.add(addColumn(tableView, "Open Play Key Passes/90", "openPlayKeyPassesPer90"));
        list.add(addColumn(tableView, "Key Passes Per 90", "keyPassesPer90"));
        list.add(addColumn(tableView, "Chances Created/90", "chancesCreatedPer90"));

        return list;
    }

    public static List<TableColumn> addCrossStatsColumns(List<TableColumn> list, TableView tableView) {
        list.add(addColumn(tableView, "Open Play Crosses Completed/90", "openPlayCrossesCompletedPer90"));
        list.add(addColumn(tableView, "Open Play Crosses Completed %", "openPlayCrossesCompletedRatio"));


        return list;
    }

    public static List<TableColumn> addAerialStatsColumns(List<TableColumn> list, TableView tableView) {
        list.add(addColumn(tableView, "Key Headers Won/90", "keyHeadersPer90"));
        list.add(addColumn(tableView, "Headers Won/90", "headersWonPer90"));
        list.add(addColumn(tableView, "Headers Won%", "headersWonRatio"));

        return list;
    }

    public static List<TableColumn> addMovementStatsColumns(List<TableColumn> list, TableView tableView) {
        list.add(addColumn(tableView, "Fouls against", "foulsAgainst"));
        list.add(addColumn(tableView, "Pressures Completed/90", "pressuresCompletedPer90"));
        list.add(addColumn(tableView, "Pressures Attempted/90", "pressuresAttemptedPer90"));
        list.add(addColumn(tableView, "Pressures Completed%", "pressuresCompletedRatio"));
        list.add(addColumn(tableView, "High Intensity Sprints/90", "sprintsPer90"));
        list.add(addColumn(tableView, "Dribbles/90", "dribblesPer90"));
        list.add(addColumn(tableView, "Distance Covered/90 in KM", "distanceCoveredPer90"));
        list.add(addColumn(tableView, "Possession Lost/90", "possessionLostPer90"));

        return list;
    }

    public static List<TableColumn> addTacklesStatsColumns(List<TableColumn> list, TableView tableView) {
        list.add(addColumn(tableView, "Fouls Committed", "foulsCommitted"));
        list.add(addColumn(tableView, "Tackles Won/90", "tacklesWonPer90"));
        list.add(addColumn(tableView, "Tackles Won%", "tacklesWonRatio"));
        list.add(addColumn(tableView, "Shots Blocked/90", "shotsBlockedPer90"));
        list.add(addColumn(tableView, "Possession Won/90", "possessionWonPer90"));
        list.add(addColumn(tableView, "Key Tackles/90", "keyTacklesPer90"));
        list.add(addColumn(tableView, "Interceptions/90", "interceptionsPer90"));
        list.add(addColumn(tableView, "Clearances/90", "clearancesPer90"));
        list.add(addColumn(tableView, "Blocks/90", "blocksPer90"));


        return list;
    }

    public static List<TableColumn> addSavesStatsColumns(List<TableColumn> list, TableView tableView) {
        list.add(addColumn(tableView, "Saves/90", "savesPer90"));
        list.add(addColumn(tableView, "Conceded/90", "concededPer90"));
        list.add(addColumn(tableView, "Clean Sheets/90", "cleanSheetsPer90"));
        list.add(addColumn(tableView, "Save%", "saveRatio"));
        list.add(addColumn(tableView, "Expected Save%", "xsaveRatio"));
        list.add(addColumn(tableView, "xGoals Prevented/90", "xGoalsPreventedPer90"));


        return list;
    }

    private static <S, T> TableColumn addColumn(TableView<S> tableView, String columName, String propertyName) {
        Label label = new Label(columName);
        label.setWrapText(true);

        TableColumn<S, T> column = new TableColumn<>();
        column.setGraphic(label);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));

        tableView.getColumns().add(column);

        return column;
    }
}
