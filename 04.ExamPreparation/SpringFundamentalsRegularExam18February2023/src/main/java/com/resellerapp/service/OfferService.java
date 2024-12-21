package com.resellerapp.service;

import com.resellerapp.model.dto.AddOfferDTO;
import com.resellerapp.model.dto.OfferDTO;
import com.resellerapp.model.entity.Offer;

import java.util.Optional;

public interface OfferService {
    void addTestOffers();

    void addOffer(AddOfferDTO addOfferDTO, Long userId);

    void buyOffer(Long id, Long id1);

    void removeOffer(Long offerId, Long userId);
}
