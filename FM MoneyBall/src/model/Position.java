package model;

public enum Position {
    GK("Goalkeeper"),
    DR("Defender (Right)"),
    DL("Defender (Left)"),
    DC("Defender (Centre)"),
    WBR("Wing Back (Right)"),
    WBL("Wing Back (Left)"),
    DM("Defensive Midfielder"),
    MR("Midfielder (Right)"),
    ML("Midfielder (Left)"),
    MC("Midfielder (Centre)"),
    AMR("Attacking Midfielder (Right)"),
    AML("Attacking Midfielder (Left)"),
    AMC("Attacking Midfielder (Centre)"),
    STC("Striker (Centre)");

    private String label;

    Position(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
