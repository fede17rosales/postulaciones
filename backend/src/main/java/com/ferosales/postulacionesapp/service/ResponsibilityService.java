package com.ferosales.postulacionesapp.service;

import com.ferosales.postulacionesapp.entity.ResponsibilityEntity;

public interface ResponsibilityService {
    void saveResponsibility(ResponsibilityEntity responsibility);
    void deleteResponsibility(Long id);
}
