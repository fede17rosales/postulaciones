package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.entity.OfferEntity;
import com.ferosales.postulacionesapp.repository.OfferRepository;
import com.ferosales.postulacionesapp.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    private OfferRepository repository;

    @Override
    public Optional<OfferEntity> findOfferById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public OfferEntity saveOffer(OfferEntity offer) {
        return repository.save(offer);
    }

    @Override
    @Transactional
    public void deleteOfferById(Long id) {
        repository.deleteById(id);
    }
}
