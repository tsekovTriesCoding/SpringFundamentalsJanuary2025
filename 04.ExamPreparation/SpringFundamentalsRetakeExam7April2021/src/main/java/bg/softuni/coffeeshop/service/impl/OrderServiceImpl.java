package bg.softuni.coffeeshop.service.impl;

import bg.softuni.coffeeshop.model.dto.AddOrderDTO;
import bg.softuni.coffeeshop.model.dto.OrderShortInfoDTO;
import bg.softuni.coffeeshop.model.entity.Category;
import bg.softuni.coffeeshop.model.entity.Order;
import bg.softuni.coffeeshop.model.entity.User;
import bg.softuni.coffeeshop.repository.CategoryRepository;
import bg.softuni.coffeeshop.repository.OrderRepository;
import bg.softuni.coffeeshop.repository.UserRepository;
import bg.softuni.coffeeshop.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Set<OrderShortInfoDTO> getAll() {
        List<Order> allOrders = this.orderRepository.findAll();

        return allOrders.stream()
                .map(o -> {
                    OrderShortInfoDTO orderShortInfoDTO = new OrderShortInfoDTO();
                    orderShortInfoDTO.setId(o.getId());
                    orderShortInfoDTO.setName(o.getName());
                    orderShortInfoDTO.setPrice(o.getPrice());
                    orderShortInfoDTO.setCategory(o.getCategory());
                    return orderShortInfoDTO;
                }).collect(Collectors.toSet());
    }

    @Transactional
    @Override
    public void remove(Long orderId) {
        Order order = this.orderRepository.findById(orderId).orElse(null);
        order.getEmployee().getOrders().remove(order);

        this.orderRepository.delete(order);
    }
}
