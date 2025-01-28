package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.entity.ResponsibilityEntity;
import com.ferosales.postulacionesapp.repository.ResponsibilityRepository;
import com.ferosales.postulacionesapp.service.ResponsibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponsibilityServiceImpl implements ResponsibilityService {
    @Autowired
    private ResponsibilityRepository responsibilityRepository;

    public void saveResponsibility(ResponsibilityEntity responsibility) {
        responsibilityRepository.save(responsibility);
    }

    @Override
    public void deleteResponsibility(Long id) {
        responsibilityRepository.deleteById(id);
    }
}
