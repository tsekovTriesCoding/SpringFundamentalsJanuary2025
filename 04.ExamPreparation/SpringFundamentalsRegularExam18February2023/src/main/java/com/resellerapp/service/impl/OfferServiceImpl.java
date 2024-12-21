package com.resellerapp.service.impl;

import com.resellerapp.model.dto.AddOfferDTO;
import com.resellerapp.model.dto.OfferDTO;
import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.enums.ConditionEnum;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.ConditionService;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {
    private final UserService userService;
    private final ConditionService conditionService;
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;

    @Autowired
    public OfferServiceImpl(UserService userService,
                            ConditionService conditionService,
                            OfferRepository offerRepository,
                            UserRepository userRepository) {
        this.userService = userService;
        this.conditionService = conditionService;
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void addTestOffers() {
        if (this.offerRepository.count() > 0) {
            return;
        }

        User admin1 = this.userService.findUserById(Long.parseLong("1")).orElse(null);
        User test1 = this.userService.findUserById(Long.parseLong("2")).orElse(null);

        Offer adminOffer = new Offer().setPrice(23.2)
                .setCondition(this.conditionService.findCondition(ConditionEnum.EXCELLENT))
                .setDescription("Sony 42\" TV");

        this.offerRepository.save(adminOffer);
        admin1.getOffers().add(adminOffer);

        Offer adminOffer2 = new Offer().setPrice(11.2)
                .setCondition(this.conditionService.findCondition(ConditionEnum.GOOD))
                .setDescription("Microwave XR32 Black");

        this.offerRepository.save(adminOffer2);
        admin1.getOffers().add(adminOffer2);

        Offer adminOffer3 = new Offer().setPrice(31.2)
                .setCondition(this.conditionService.findCondition(ConditionEnum.GOOD))
                .setDescription("Laptop 5730");

        this.offerRepository.save(adminOffer3);
        admin1.getOffers().add(adminOffer3);

        Offer adminOffer4 = new Offer().setPrice(41.2)
                .setCondition(this.conditionService.findCondition(ConditionEnum.GOOD))
                .setDescription("Overplay Album");

        this.offerRepository.save(adminOffer4);
        admin1.getOffers().add(adminOffer4);

        Offer adminOffer5 = new Offer().setPrice(51.2)
                .setCondition(this.conditionService.findCondition(ConditionEnum.GOOD))
                .setDescription("Round Table 120cm");

        this.offerRepository.save(adminOffer5);
        admin1.getOffers().add(adminOffer5);

        Offer testOffer1 = new Offer().setPrice(19.23)
                .setCondition(this.conditionService.findCondition(ConditionEnum.EXCELLENT))
                .setDescription("Pepper Roaster");

        this.offerRepository.save(testOffer1);
        test1.getOffers().add(testOffer1);

        Offer testOffer2 = new Offer().setPrice(92.02)
                .setCondition(this.conditionService.findCondition(ConditionEnum.ACCEPTABLE))
                .setDescription("PS4 Joystick");

        this.offerRepository.save(testOffer2);
        test1.getOffers().add(testOffer2);

        Offer adminBought1 = new Offer().setPrice(13.23)
                .setCondition(this.conditionService.findCondition(ConditionEnum.ACCEPTABLE))
                .setDescription("Microphone Wireless");

        this.offerRepository.save(adminBought1);
        admin1.getBoughtOffers().add(adminBought1);

        Offer adminBought2 = new Offer().setPrice(223.23)
                .setCondition(this.conditionService.findCondition(ConditionEnum.ACCEPTABLE))
                .setDescription("Vacuum Cleaner Ultra");

        this.offerRepository.save(adminBought2);
        admin1.getBoughtOffers().add(adminBought2);

        this.userRepository.save(admin1);
        this.userRepository.save(test1);
    }

    @Override
    public void addOffer(AddOfferDTO addOfferDTO, Long userId) {
        Offer offer = new Offer();
        Condition condition = this.conditionService.findCondition(addOfferDTO.getCondition());

        offer.setDescription(addOfferDTO.getDescription())
                .setPrice(addOfferDTO.getPrice())
                .setCondition(condition);

        User user = this.userService.findUserById(userId).orElse(null);
        user.getOffers().add(offer);

        this.offerRepository.save(offer);
        this.userRepository.save(user);
    }

    @Override
    public void buyOffer(Long offerId, Long userId) {
        User buyer = this.userService.findUserById(userId).orElse(null);
        User seller = this.userRepository.findUserByOffers_Id(offerId).orElse(null);

        Offer offer = seller.getOffers().stream().filter(e -> e.getId() == offerId).findFirst().orElse(null);
        boolean remove = seller.getOffers().remove(offer);
        boolean add = buyer.getBoughtOffers().add(offer);

        this.userRepository.save(seller);
        this.userRepository.save(buyer);
    }

    @Override
    public void removeOffer(Long offerId, Long userId) {
        User user = this.userService.findUserById(userId).orElse(null);
        Offer offer = user.getOffers().stream().filter(e -> e.getId() == offerId).findFirst().orElse(null);

        user.getOffers().remove(offer);

        this.userRepository.save(user);
        this.offerRepository.delete(offer);
    }


}
