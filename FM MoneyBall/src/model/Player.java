package model;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private final String name;
    private final int age;
    private final String positionString;
    private Set<Position> positions = new HashSet<>();
    private final String nationality;
    private final int height;
    private final int weight;
    private final String personality;
    private final String injurySusceptibility;
    private final String recurringInjury;

    public Player(String name, int age, String positionString, String nationality,
                  int height, int weight, String personality, String injurySusceptibility,
                  String recurringInjury) {
        this.name = name;
        this.age = age;
        this.positionString = positionString;
        this.nationality = nationality;
        this.height = height;
        this.weight = weight;
        this.personality = personality;
        this.injurySusceptibility = injurySusceptibility;
        this.recurringInjury = recurringInjury;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPositionString() {
        return positionString;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public String getNationality() {
        return nationality;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getPersonality() {
        return personality;
    }

    public String getInjurySusceptibility() {
        return injurySusceptibility;
    }

    public String getRecurringInjury() {
        return recurringInjury;
    }

    public void addPosition(Position position) {
        positions.add(position);
    }
}
