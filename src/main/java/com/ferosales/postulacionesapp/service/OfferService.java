package com.ferosales.postulacionesapp.service;

import com.ferosales.postulacionesapp.entity.OfferEntity;

import java.util.Optional;

public interface OfferService {

    Optional<OfferEntity> findOfferById(Long id);
    OfferEntity saveOffer(OfferEntity offer);
    void deleteOfferById(Long id);
}
