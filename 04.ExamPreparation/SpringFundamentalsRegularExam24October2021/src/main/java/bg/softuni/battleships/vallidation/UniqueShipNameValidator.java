package bg.softuni.battleships.vallidation;

import bg.softuni.battleships.service.ShipService;
import bg.softuni.battleships.vallidation.annotation.UniqueShipName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueShipNameValidator implements ConstraintValidator<UniqueShipName, String> {
    private final ShipService shipService;

    public UniqueShipNameValidator(ShipService shipService) {
        this.shipService = shipService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.shipService.getShipByName(value) == null;
    }
}
