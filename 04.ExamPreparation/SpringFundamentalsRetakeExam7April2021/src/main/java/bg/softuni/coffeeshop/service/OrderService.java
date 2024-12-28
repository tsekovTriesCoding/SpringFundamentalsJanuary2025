package bg.softuni.coffeeshop.service;

import bg.softuni.coffeeshop.model.dto.AddOrderDTO;
import bg.softuni.coffeeshop.model.dto.OrderShortInfoDTO;

import java.util.Set;

public interface OrderService {
    void add(AddOrderDTO addOrderDTO, Long id);

    Set<OrderShortInfoDTO> getAll();

    void remove(Long orderId);
}
