package bg.softuni.coffeeshop.service;

import bg.softuni.coffeeshop.model.Order;
import bg.softuni.coffeeshop.model.dto.AddOrderDTO;

import java.util.Set;

public interface OrderService {
    void add(AddOrderDTO addOrderDTO, Long id);

    Set<Order> getAllCoffeeOrders();

    Set<Order> getAllCakeOrders();

    Set<Order> getAllDrinkOrders();

    Set<Order> getAllOtherOrders();
}
