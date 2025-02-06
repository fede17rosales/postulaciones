package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.entity.PublicationEntity;
import com.ferosales.postulacionesapp.repository.PublicationRepository;
import com.ferosales.postulacionesapp.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    private PublicationRepository repository;

    @Override
    public List<PublicationEntity> viewPublications() {
        return repository.findAllByOrderByIdDesc();
    }

    @Override
    public Optional<PublicationEntity> getPublication(Long id) {
        return repository.findById(id);
    }

    @Override
    public void savePublication(PublicationEntity publication) {
            repository.save(publication);
    }

    @Override
    public void deletePublication(Long id) {
        repository.deleteById(id);
    }
}
