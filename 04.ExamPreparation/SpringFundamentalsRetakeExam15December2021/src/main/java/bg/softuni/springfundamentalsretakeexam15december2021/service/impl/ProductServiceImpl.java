package bg.softuni.springfundamentalsretakeexam15december2021.service.impl;

import bg.softuni.springfundamentalsretakeexam15december2021.model.dto.AddProductDTO;
import bg.softuni.springfundamentalsretakeexam15december2021.model.dto.ProductInfoDTO;
import bg.softuni.springfundamentalsretakeexam15december2021.model.entity.Category;
import bg.softuni.springfundamentalsretakeexam15december2021.model.entity.Product;
import bg.softuni.springfundamentalsretakeexam15december2021.model.enums.CategoryEnum;
import bg.softuni.springfundamentalsretakeexam15december2021.repository.CategoryRepository;
import bg.softuni.springfundamentalsretakeexam15december2021.repository.ProductRepository;
import bg.softuni.springfundamentalsretakeexam15december2021.repository.UserRepository;
import bg.softuni.springfundamentalsretakeexam15december2021.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

        Product product = new Product();
        product.setName("Milk")
                .setCategory(this.categoryRepository.getByName(CategoryEnum.FOOD))
                .setPrice(BigDecimal.valueOf(2.00));

        this.productRepository.save(product);

        Product product1 = new Product();
        product1.setName("Bread")
                .setCategory(this.categoryRepository.getByName(CategoryEnum.FOOD))
                .setPrice(BigDecimal.valueOf(1.00));

        this.productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Grape")
                .setCategory(this.categoryRepository.getByName(CategoryEnum.FOOD))
                .setPrice(BigDecimal.valueOf(5.00));

        this.productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("Cleaning Supplier")
                .setCategory(this.categoryRepository.getByName(CategoryEnum.HOUSEHOLD))
                .setPrice(BigDecimal.valueOf(8.00));

        this.productRepository.save(product3);

        Product product4 = new Product();
        product4.setName("Beer")
                .setCategory(this.categoryRepository.getByName(CategoryEnum.DRINK))
                .setPrice(BigDecimal.valueOf(3.00));

        this.productRepository.save(product4);

        Product product5 = new Product();
        product5.setName("Wine")
                .setCategory(this.categoryRepository.getByName(CategoryEnum.DRINK))
                .setPrice(BigDecimal.valueOf(12.00));

        this.productRepository.save(product5);

        Product product6 = new Product();
        product6.setName("Shower Gel")
                .setCategory(this.categoryRepository.getByName(CategoryEnum.OTHER))
                .setPrice(BigDecimal.valueOf(10.00));

        this.productRepository.save(product6);
    }

    @Override
    public List<ProductInfoDTO> getAllFoodProducts() {
        Category category = this.categoryRepository.getByName(CategoryEnum.FOOD);
        return getProductInfoDTOS(category);
    }

    @Override
    public List<ProductInfoDTO> getAllDrinkProducts() {
        Category category = this.categoryRepository.getByName(CategoryEnum.DRINK);
        return getProductInfoDTOS(category);
    }

    @Override
    public List<ProductInfoDTO> getAllHouseholdProducts() {
        Category category = this.categoryRepository.getByName(CategoryEnum.HOUSEHOLD);
        return getProductInfoDTOS(category);
    }

    @Override
    public List<ProductInfoDTO> getAllOtherProducts() {
        Category category = this.categoryRepository.getByName(CategoryEnum.OTHER);
        return getProductInfoDTOS(category);
    }

    @Override
    public void addProduct(AddProductDTO addProductDTO) {
        Product product = new Product();
        Category category = this.categoryRepository.getByName(addProductDTO.getCategory());

        product.setName(addProductDTO.getName());
        product.setCategory(category);
        product.setPrice(addProductDTO.getPrice());
        product.setDescription(addProductDTO.getDescription());
        product.setNeededBefore(addProductDTO.getNeededBefore());
        this.productRepository.save(product);
    }

    @Override
    public Product getProductByName(String name) {
        return this.productRepository.findByName(name).orElse(null);
    }

    @Override
    public void buyProduct(Long productId) {
        Product product = this.productRepository.findById(productId).orElse(null);

        this.productRepository.delete(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public void buyAllProducts() {
        this.productRepository.deleteAll();
    }

    private List<ProductInfoDTO> getProductInfoDTOS(Category name) {
        List<Product> allProductsByCategory = this.productRepository.getAllByCategory(name);

        return allProductsByCategory.stream().map(product -> {
            ProductInfoDTO productInfoDTO = new ProductInfoDTO();
            productInfoDTO.setId(product.getId());
            productInfoDTO.setName(product.getName());
            productInfoDTO.setPrice(product.getPrice());
            return productInfoDTO;
        }).toList();
    }

}
