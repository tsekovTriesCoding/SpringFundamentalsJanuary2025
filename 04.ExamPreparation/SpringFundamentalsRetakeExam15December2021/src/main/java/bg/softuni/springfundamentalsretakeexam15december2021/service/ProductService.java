package bg.softuni.springfundamentalsretakeexam15december2021.service;

import bg.softuni.springfundamentalsretakeexam15december2021.model.dto.AddProductDTO;
import bg.softuni.springfundamentalsretakeexam15december2021.model.dto.ProductInfoDTO;
import bg.softuni.springfundamentalsretakeexam15december2021.model.entity.Product;

import java.util.List;

public interface ProductService {
    void initProducts();

    List<ProductInfoDTO> getAllFoodProducts();

    List<ProductInfoDTO> getAllDrinkProducts();

    List<ProductInfoDTO> getAllHouseholdProducts();

    List<ProductInfoDTO> getAllOtherProducts();

    void addProduct(AddProductDTO addProductDTO);

    Product getProductByName(String name);

    void buyProduct(Long productId);

    List<Product> getAllProducts();

    void buyAllProducts();
}
