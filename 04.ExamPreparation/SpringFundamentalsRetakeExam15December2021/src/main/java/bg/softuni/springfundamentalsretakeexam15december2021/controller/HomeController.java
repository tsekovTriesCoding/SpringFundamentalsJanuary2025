package bg.softuni.springfundamentalsretakeexam15december2021.controller;


import bg.softuni.springfundamentalsretakeexam15december2021.model.dto.ProductInfoDTO;
import bg.softuni.springfundamentalsretakeexam15december2021.model.entity.Product;
import bg.softuni.springfundamentalsretakeexam15december2021.model.enums.CategoryEnum;
import bg.softuni.springfundamentalsretakeexam15december2021.service.ProductService;
import bg.softuni.springfundamentalsretakeexam15december2021.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.DoubleStream;

@Controller
@RequestMapping("/")
public class HomeController {
    private final LoggedUser loggedUser;
    private final ProductService productService;

    public HomeController(LoggedUser loggedUser,
                          ProductService productService) {
        this.loggedUser = loggedUser;
        this.productService = productService;
    }

    @GetMapping
    String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("home")
    String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        List<ProductInfoDTO> allFoodProducts = this.productService.getAllFoodProducts();
        model.addAttribute("allFoodProducts", allFoodProducts);

        List<ProductInfoDTO> allDrinkProducts = this.productService.getAllDrinkProducts();
        model.addAttribute("allDrinkProducts", allDrinkProducts);

        List<ProductInfoDTO> allHouseholdProducts = this.productService.getAllHouseholdProducts();
        model.addAttribute("allHouseholdProducts", allHouseholdProducts);

        List<ProductInfoDTO> allOtherProducts = this.productService.getAllOtherProducts();
        model.addAttribute("allOtherProducts", allOtherProducts);

        BigDecimal sum = this.productService.getAllProducts()
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("sum", sum);

        return "home";
    }
}
