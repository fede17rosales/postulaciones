package com.ferosales.postulacionesapp.service;

import com.ferosales.postulacionesapp.entity.OfferEntity;
import com.ferosales.postulacionesapp.service.impl.OfferServiceImpl;

import java.util.List;
import java.util.Optional;

public interface OfferService {

    Optional<OfferEntity> findOfferById(Long id);
    OfferEntity saveOffer(OfferEntity offer);
    void deleteOfferById(Long id);
}
