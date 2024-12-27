package bg.softuni.battleships.service;

import bg.softuni.battleships.model.dto.AddShipDTO;
import bg.softuni.battleships.model.dto.ShipShortInfoDTO;
import bg.softuni.battleships.model.entity.Ship;

import java.util.Set;

public interface ShipService {
    void addShip(AddShipDTO addShipDTO, Long userId);

    Ship getShipByName(String value);

    void initShips();

    void engage(String attackerName, String defenderName);

    Set<ShipShortInfoDTO> getAllShips();
}
