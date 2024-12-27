package bg.softuni.battleships.model.dto;

public class ShipAttackNamesDTO {
    private String attackerName;
    private String defenderName;

    public ShipAttackNamesDTO() {
    }

    public String getAttackerName() {
        return attackerName;
    }

    public ShipAttackNamesDTO setAttackerName(String attackerName) {
        this.attackerName = attackerName;
        return this;
    }

    public String getDefenderName() {
        return defenderName;
    }

    public ShipAttackNamesDTO setDefenderName(String defenderName) {
        this.defenderName = defenderName;
        return this;
    }
}
