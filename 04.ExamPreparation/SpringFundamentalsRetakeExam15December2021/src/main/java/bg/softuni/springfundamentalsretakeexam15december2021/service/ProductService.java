package bg.softuni.springfundamentalsretakeexam15december2021.service;

import bg.softuni.springfundamentalsretakeexam15december2021.model.dto.ProductInfoDTO;

import java.util.List;

public interface ProductService {
    void initProducts();

    List<ProductInfoDTO> getAllFoodProducts();

    List<ProductInfoDTO> getAllDrinkProducts();

    List<ProductInfoDTO> getAllHouseholdProducts();

    List<ProductInfoDTO> getAllOtherProducts();
}
