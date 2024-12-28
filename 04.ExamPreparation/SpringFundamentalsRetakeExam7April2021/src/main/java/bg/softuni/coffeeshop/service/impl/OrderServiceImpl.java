package bg.softuni.coffeeshop.service.impl;

import bg.softuni.coffeeshop.model.Category;
import bg.softuni.coffeeshop.model.Order;
import bg.softuni.coffeeshop.model.User;
import bg.softuni.coffeeshop.model.dto.AddOrderDTO;
import bg.softuni.coffeeshop.model.enums.CategoryEnum;
import bg.softuni.coffeeshop.repository.CategoryRepository;
import bg.softuni.coffeeshop.repository.OrderRepository;
import bg.softuni.coffeeshop.repository.UserRepository;
import bg.softuni.coffeeshop.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(UserRepository userRepository,
                            CategoryRepository categoryRepository,
                            OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    @Override
    public void add(AddOrderDTO addOrderDTO, Long userId) {
        User user = this.userRepository.findById(userId).orElse(null);
        Order order = new Order();
        order.setName(addOrderDTO.getName());
        order.setPrice(addOrderDTO.getPrice());
        order.setOrderTime(addOrderDTO.getOrderTime());

        Category category = this.categoryRepository.getCategoryByName(addOrderDTO.getCategory());
        order.setCategory(category);
        order.setDescription(addOrderDTO.getDescription());
        order.setEmployee(user);

        this.orderRepository.save(order);
        user.getOrders().add(order);
    }

    @Override
    public Set<Order> getAllCoffeeOrders() {
        Category category = this.categoryRepository.getCategoryByName(CategoryEnum.COFFEE);
        return this.orderRepository.getAllByCategory(category);
    }

    @Override
    public Set<Order> getAllCakeOrders() {
        Category category = this.categoryRepository.getCategoryByName(CategoryEnum.CAKE);
        return this.orderRepository.getAllByCategory(category);
    }

    @Override
    public Set<Order> getAllDrinkOrders() {
        Category category = this.categoryRepository.getCategoryByName(CategoryEnum.DRINK);
        return this.orderRepository.getAllByCategory(category);
    }

    @Override
    public Set<Order> getAllOtherOrders() {
        Category category = this.categoryRepository.getCategoryByName(CategoryEnum.OTHER);
        return this.orderRepository.getAllByCategory(category);
    }
}
