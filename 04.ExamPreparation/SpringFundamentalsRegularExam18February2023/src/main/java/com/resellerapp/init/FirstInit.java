package com.resellerapp.init;

import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import com.resellerapp.service.impl.ConditionServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {
    private final ConditionServiceImpl conditionService;
    private final UserService userService;
    private final OfferService offerService;

    public FirstInit(ConditionServiceImpl conditionService, UserService userService, OfferService offerService) {
        this.conditionService = conditionService;
        this.userService = userService;
        this.offerService = offerService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userService.initAdmin();
        this.userService.initTest();
        this.conditionService.initConditions();
        this.offerService.addTestOffers();
    }
}
