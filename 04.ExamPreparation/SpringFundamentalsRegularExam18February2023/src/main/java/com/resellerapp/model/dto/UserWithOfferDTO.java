package com.resellerapp.model.dto;

import java.util.List;
import java.util.Set;

public class UserWithOfferDTO {
    private String username;
    private Long id;
    private List<OfferDTO> offers;

    public UserWithOfferDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserWithOfferDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserWithOfferDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public List<OfferDTO> getOffers() {
        return offers;
    }

    public UserWithOfferDTO setOffers(List<OfferDTO> offers) {
        this.offers = offers;
        return this;
    }
}
