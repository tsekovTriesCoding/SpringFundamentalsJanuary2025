package bg.softuni.battleships.service;

import bg.softuni.battleships.model.dto.AddShipDTO;
import bg.softuni.battleships.model.entity.Ship;

public interface ShipService {
    void addShip(AddShipDTO addShipDTO, Long userId);

    Ship getShipByName(String value);
}
