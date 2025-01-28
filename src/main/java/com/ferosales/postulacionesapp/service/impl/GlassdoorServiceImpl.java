package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.entity.GlassdoorEntity;
import com.ferosales.postulacionesapp.repository.GlassdoorRepository;
import com.ferosales.postulacionesapp.service.GlassdoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GlassdoorServiceImpl implements GlassdoorService {

    @Autowired
    private GlassdoorRepository repository;

    @Override
    public GlassdoorEntity saveGlassdoor(GlassdoorEntity glassdoor) {
        return repository.save(glassdoor);
    }

    @Override
    public void deleteGlassdorById(Long id) {
        repository.deleteById(id);
    }
}
