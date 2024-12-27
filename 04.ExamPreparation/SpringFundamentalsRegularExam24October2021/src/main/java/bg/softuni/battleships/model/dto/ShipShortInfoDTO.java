package bg.softuni.battleships.model.dto;

public class ShipShortInfoDTO {
    private String name;
    private Long health;
    private Long power;

    public ShipShortInfoDTO() {
    }

    public String getName() {
        return name;
    }

    public ShipShortInfoDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Long getHealth() {
        return health;
    }

    public ShipShortInfoDTO setHealth(Long health) {
        this.health = health;
        return this;
    }

    public Long getPower() {
        return power;
    }

    public ShipShortInfoDTO setPower(Long power) {
        this.power = power;
        return this;
    }
}
