package bg.softuni.coffeeshop.service;

import bg.softuni.coffeeshop.model.dto.AddOrderDTO;

public interface OrderService {
    void add(AddOrderDTO addOrderDTO, Long id);
}
