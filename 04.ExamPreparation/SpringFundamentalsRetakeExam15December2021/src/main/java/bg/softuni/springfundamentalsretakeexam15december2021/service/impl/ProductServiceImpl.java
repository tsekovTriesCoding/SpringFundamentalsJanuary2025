package bg.softuni.springfundamentalsretakeexam15december2021.service.impl;

import bg.softuni.springfundamentalsretakeexam15december2021.model.entity.Product;
import bg.softuni.springfundamentalsretakeexam15december2021.model.entity.User;
import bg.softuni.springfundamentalsretakeexam15december2021.model.enums.CategoryEnum;
import bg.softuni.springfundamentalsretakeexam15december2021.repository.CategoryRepository;
import bg.softuni.springfundamentalsretakeexam15december2021.repository.ProductRepository;
import bg.softuni.springfundamentalsretakeexam15december2021.repository.UserRepository;
import bg.softuni.springfundamentalsretakeexam15december2021.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              UserRepository userRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initProducts() {
        if (this.productRepository.count() > 0) {
            return;
        }

        User admin = this.userRepository.findById(Long.parseLong("1")).orElse(null);
        User user = this.userRepository.findById(Long.parseLong("2")).orElse(null);

        Product product = new Product();
        product.setName("Milk")
                .setCategory(this.categoryRepository.getByName(CategoryEnum.FOOD))
                .setPrice(2.00);

        this.productRepository.save(product);

        Product product1 = new Product();
        product1.setName("Bread")
                .setCategory(this.categoryRepository.getByName(CategoryEnum.FOOD))
                .setPrice(1.00);

        this.productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Grape")
                .setCategory(this.categoryRepository.getByName(CategoryEnum.FOOD))
                .setPrice(5.00);

        this.productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("Cleaning Supplier")
                .setCategory(this.categoryRepository.getByName(CategoryEnum.HOUSEHOLD))
                .setPrice(8.00);

        this.productRepository.save(product3);

        Product product4 = new Product();
        product4.setName("Beer")
                .setCategory(this.categoryRepository.getByName(CategoryEnum.DRINK))
                .setPrice(3.00);

        this.productRepository.save(product4);

        Product product5 = new Product();
        product5.setName("Wine")
                .setCategory(this.categoryRepository.getByName(CategoryEnum.FOOD))
                .setPrice(12.00);

        this.productRepository.save(product5);

        Product product6 = new Product();
        product6.setName("Shower Gel")
                .setCategory(this.categoryRepository.getByName(CategoryEnum.FOOD))
                .setPrice(10.00);

        this.productRepository.save(product6);
    }
}
