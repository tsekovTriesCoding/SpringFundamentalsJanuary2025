package com.resellerapp.service;

import com.resellerapp.model.dto.AddOfferDTO;

public interface OfferService {
    void addTestOffers();

    void addOffer(AddOfferDTO addOfferDTO, Long userId);
}
