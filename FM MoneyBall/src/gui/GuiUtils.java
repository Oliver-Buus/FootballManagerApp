package gui;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuiUtils {

    public static <S> void createWageComparator(TableColumn<S, String> wageColumn) {
        Comparator<String> wageComparator = (wage1, wage2) -> {
            int wage1Value = Integer.parseInt(wage1.replaceAll("[^0-9]", ""));
            int wage2Value = Integer.parseInt(wage2.replaceAll("[^0-9]", ""));
            return Integer.compare(wage1Value, wage2Value);
        };
        wageColumn.setComparator(wageComparator);
    }


}
