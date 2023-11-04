package gui;

import javafx.css.Match;
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

    public static <S> void createAppsComparator(TableColumn<S, String> appsColumn) {
        Comparator<String> appsComparator = (apps1, apps2) -> {
            int apps1Value = extractValue(apps1);
            int apps2Value = extractValue(apps2);

            if (apps1Value != apps2Value) {
                return Integer.compare(apps1Value, apps2Value);
            } else {
                int paren1Value = extractParenValue(apps1);
                int paren2Value = extractParenValue(apps2);
                return Integer.compare(paren1Value, paren2Value);
            }
        };
        appsColumn.setComparator(appsComparator);
    }

    private static int extractValue(String apps) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(apps);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }

        return 0;
    }

    private static int extractParenValue(String apps) {
        Pattern pattern = Pattern.compile("\\((\\d+)\\)");
        Matcher matcher = pattern.matcher(apps);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }

        return 0;
    }


}
