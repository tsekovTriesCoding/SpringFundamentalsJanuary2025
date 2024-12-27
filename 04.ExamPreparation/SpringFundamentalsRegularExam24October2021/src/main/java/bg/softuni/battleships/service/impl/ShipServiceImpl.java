package bg.softuni.battleships.service.impl;

import bg.softuni.battleships.model.dto.AddShipDTO;
import bg.softuni.battleships.model.entity.Category;
import bg.softuni.battleships.model.entity.Ship;
import bg.softuni.battleships.model.entity.User;
import bg.softuni.battleships.repository.CategoryRepository;
import bg.softuni.battleships.repository.ShipRepository;
import bg.softuni.battleships.repository.UserRepository;
import bg.softuni.battleships.service.ShipService;
import org.springframework.stereotype.Service;

@Service
public class ShipServiceImpl implements ShipService {
    private final ShipRepository shipRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ShipServiceImpl(ShipRepository shipRepository,
                           CategoryRepository categoryRepository,
                           UserRepository userRepository) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addShip(AddShipDTO addShipDTO, Long userId) {
        Ship ship = new Ship();
        User user = this.userRepository.findById(userId).orElse(null);
        Category category = this.categoryRepository.getCategoryByName(addShipDTO.getCategory());

        ship.setName(addShipDTO.getName());
        ship.setHealth(addShipDTO.getHealth());
        ship.setPower(addShipDTO.getPower());
        ship.setCreated(addShipDTO.getCreated());
        ship.setCategory(category);
        ship.setUser(user);

        this.shipRepository.save(ship);
    }

    @Override
    public Ship getShipByName(String value) {
        return this.shipRepository.findShipByName(value).orElse(null);
    }
}
