package gui.filters;

import model.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FilterUtil {

    public static Map<String, String> readNationalitiesFile() {
        String fileName = "C:\\Users\\failo\\Documents\\GitHub\\FootballManagerApp\\FM MoneyBall\\src\\resources\\Nations.txt";
        Map<String, String> nationalityMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    nationalityMap.put(key, value);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Map.Entry<String, String>> entryList = new ArrayList<>(nationalityMap.entrySet());
        entryList.sort(Map.Entry.comparingByValue());

        Map<String, String> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : entryList)
            sortedMap.put(entry.getKey(), entry.getValue());

        return sortedMap;
    }

    public static void addOptionsToNationalityCombobox(List<Player> players) {
        List<String> nationalityList = new ArrayList<>();

        Map<String, String> nationalityMap = readNationalitiesFile();

        for (Player p : players) {
            for (Map.Entry<String, String> entry : nationalityMap.entrySet()) {
                if (p.getNationality().equals(entry.getKey()) &&
                        !nationalityList.contains(entry.getValue())) {
                    nationalityList.add(entry.getValue());
                }
            }
        }

        Collections.sort(nationalityList);
        nationalityList.add(0, "Any");
        for (String s : nationalityList) {
            if (!BaseFilters.getCbbNationalities().getItems().contains(s))
                BaseFilters.getCbbNationalities().getItems().add(s);
        }
    }

    public static void addOptionsToDivisionCombobox(List<Player> players) {
        List<String> divisionList = new ArrayList<>();

        for (Player p : players) {
            if (!divisionList.contains(p.getDivision()) && !p.getDivision().equals("-")) {
                divisionList.add(p.getDivision());
            }
        }
        Collections.sort(divisionList);
        divisionList.add(0,"Any");
        for (String s : divisionList) {
            if (!BaseFilters.getCbbDivision().getItems().contains(s))
                BaseFilters.getCbbDivision().getItems().add(s);
        }
    }

    public static void addOptionsToClubCombobox(List<Player> players) {
        List<String> ClubList = new ArrayList<>();

        for (Player p : players) {
            if (!ClubList.contains(p.getClub())) {
                ClubList.add(p.getClub());
            }
        }

        Collections.sort(ClubList);
        ClubList.add(0, "Any");
        for (String s : ClubList) {
            if (!BaseFilters.getCbbClub().getItems().contains(s))
                BaseFilters.getCbbClub().getItems().add(s);
        }
    }
}
