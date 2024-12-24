package bg.softuni.linkedout.web;

import bg.softuni.linkedout.model.dto.CreateCompanyDTO;
import bg.softuni.linkedout.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AddCompanyController {
    private final CompanyService companyService;

    public AddCompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping({"/companies/add"})
    public String addCompanyView(Model model) {
        if (!model.containsAttribute("company")) {
            model.addAttribute("company", new CreateCompanyDTO());
        }

        return "company-add";
    }

    @PostMapping({"/companies/add"})
    public String addCompany(@Valid CreateCompanyDTO companyDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("company", companyDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.company", bindingResult);
            return "redirect:/companies/add";
        } else {
            this.companyService.saveCompany(companyDTO);
            return "redirect:/companies/add";
        }
    }
}