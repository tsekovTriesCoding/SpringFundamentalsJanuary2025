package bg.softuni.battleships.service.impl;

import bg.softuni.battleships.model.dto.AddShipDTO;
import bg.softuni.battleships.model.dto.ShipShortInfoDTO;
import bg.softuni.battleships.model.entity.Category;
import bg.softuni.battleships.model.entity.Ship;
import bg.softuni.battleships.model.entity.User;
import bg.softuni.battleships.model.enums.CategoryEnum;
import bg.softuni.battleships.repository.CategoryRepository;
import bg.softuni.battleships.repository.ShipRepository;
import bg.softuni.battleships.repository.UserRepository;
import bg.softuni.battleships.service.ShipService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public void initShips() {
        if (this.shipRepository.count() > 0) {
            return;
        }

        User admin = this.userRepository.findById(1L).orElse(null);
        User user = this.userRepository.findById(2L).orElse(null);

        Ship ship1 = new Ship();
        ship1.setName("Battleship 1");
        ship1.setPower(100L);
        ship1.setHealth(1000L);
        ship1.setCreated(LocalDate.now());

        Category ship1Category = this.categoryRepository.getCategoryByName(CategoryEnum.BATTLE);
        ship1.setCategory(ship1Category);
        ship1.setUser(admin);

        this.shipRepository.save(ship1);
        admin.getShips().add(ship1);

        Ship ship2 = new Ship();
        ship2.setName("Cargo 1");
        ship2.setPower(50L);
        ship2.setHealth(100L);
        ship2.setCreated(LocalDate.now());

        Category ship2Category = this.categoryRepository.getCategoryByName(CategoryEnum.CARGO);
        ship2.setCategory(ship2Category);
        ship2.setUser(admin);

        this.shipRepository.save(ship2);
        admin.getShips().add(ship2);

        Ship ship3 = new Ship();
        ship3.setName("Patrol 1");
        ship3.setPower(500L);
        ship3.setHealth(345L);
        ship3.setCreated(LocalDate.now());

        Category ship3Category = this.categoryRepository.getCategoryByName(CategoryEnum.PATROL);
        ship3.setCategory(ship3Category);
        ship3.setUser(user);

        this.shipRepository.save(ship3);
        user.getShips().add(ship3);

        Ship ship4 = new Ship();
        ship4.setName("Battleship 2");
        ship4.setPower(652L);
        ship4.setHealth(800L);
        ship4.setCreated(LocalDate.now());

        Category ship4Category = this.categoryRepository.getCategoryByName(CategoryEnum.BATTLE);
        ship4.setCategory(ship4Category);
        ship4.setUser(user);

        this.shipRepository.save(ship4);
        user.getShips().add(ship4);
    }

    @Override
    public void engage(String attackerName, String defenderName) {
        Ship attacker = this.shipRepository.findShipByName(attackerName).orElse(null);
        Ship defender = this.shipRepository.findShipByName(defenderName).orElse(null);

        if (attacker == null || defender == null) {
            return;
        }

        defender.takeDamage(attacker.getPower());
        this.shipRepository.save(defender);

        if (defender.getHealth() <= 0) {
            this.shipRepository.delete(defender);
            User defenderUser = defender.getUser();
            defenderUser.getShips().remove(defender);
        }
    }

    @Override
    public Set<ShipShortInfoDTO> getAllShips() {
        List<Ship> allShips = this.shipRepository.findAll();

        return allShips.stream().map(s -> {
            ShipShortInfoDTO shipShortInfoDTO = new ShipShortInfoDTO();
            shipShortInfoDTO.setName(s.getName());
            shipShortInfoDTO.setHealth(s.getHealth());
            shipShortInfoDTO.setPower(s.getPower());
            return shipShortInfoDTO;
        }).collect(Collectors.toSet());
    }
}
