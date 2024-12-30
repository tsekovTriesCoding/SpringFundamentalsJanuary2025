package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.Picture;
import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.service.RouteService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final Random random = new Random();

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
        this.modelMapper = new ModelMapper();
    }

    @Transactional
    public List<RouteShortInfoDTO> getAll() {
        return this.routeRepository.findAll().stream().map(this::mapToShortInfo).toList();
    }

    @Transactional
    public RouteShortInfoDTO getRandomRoute() {
        long routeCount = this.routeRepository.count();
        long randomId = this.random.nextLong(routeCount) + 1;
        Optional<Route> route = this.routeRepository.findById(randomId);
        if (route.isEmpty()) {

        }

        return this.mapToShortInfo(route.get());
    }

    private RouteShortInfoDTO mapToShortInfo(Route route) {
        RouteShortInfoDTO dto = this.modelMapper.map(route, RouteShortInfoDTO.class);
        Optional<Picture> first = route.getPictures().stream().findFirst();
        first.ifPresent((picture) -> {
            dto.setImageUrl(picture.getUrl());
        });
        return dto;
    }
}