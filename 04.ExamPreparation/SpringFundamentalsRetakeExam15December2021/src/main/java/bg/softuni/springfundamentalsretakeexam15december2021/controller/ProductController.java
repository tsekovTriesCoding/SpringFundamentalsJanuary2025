package bg.softuni.springfundamentalsretakeexam15december2021.controller;

import bg.softuni.springfundamentalsretakeexam15december2021.model.dto.AddProductDTO;
import bg.softuni.springfundamentalsretakeexam15december2021.service.ProductService;
import bg.softuni.springfundamentalsretakeexam15december2021.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final LoggedUser loggedUser;
    private final ProductService productService;

    public ProductController(LoggedUser loggedUser, ProductService productService) {
        this.loggedUser = loggedUser;
        this.productService = productService;
    }

    @ModelAttribute
    public AddProductDTO addProductDTO() {
        return new AddProductDTO();
    }

    @GetMapping("/add-product")
    public String addProduct() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        return "product-add";
    }

    @PostMapping("/add-product")
    public String addProduct(@Valid AddProductDTO addProductDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addProductDTO", addProductDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addProductDTO", result);

            return "redirect:/products/add-product";
        }

        this.productService.addProduct(addProductDTO);

        return "redirect:/home";
    }

    @GetMapping("/buy-product/{id}")
    public String buyProduct(@PathVariable Long id) {
        this.productService.buyProduct(id);
        return "redirect:/home";
    }

    @GetMapping("/buy-all-products")
    public String buyAllProduct() {
        this.productService.buyAllProducts();
        return "redirect:/home";
    }
}
