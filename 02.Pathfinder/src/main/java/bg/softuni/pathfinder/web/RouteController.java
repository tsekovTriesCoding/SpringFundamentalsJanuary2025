package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import bg.softuni.pathfinder.model.enums.CategoryType;
import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.service.impl.RouteServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RouteController {
    private final RouteServiceImpl routeService;

    public RouteController(RouteServiceImpl routeService) {
        this.routeService = routeService;
    }

    @GetMapping({"/routes"})
    public String routes(Model model) {
        List<RouteShortInfoDTO> routes = this.routeService.getAll();
        model.addAttribute("allRoutes", routes);
        return "routes";
    }

    @GetMapping({"/add-route"})
    public String addRoute(Model model) {
        model.addAttribute("allLevels", Level.values());
        model.addAttribute("categories", CategoryType.values());
        return "/add-route";
    }
}