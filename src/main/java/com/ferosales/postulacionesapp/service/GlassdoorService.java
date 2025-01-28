package com.ferosales.postulacionesapp.service;

import com.ferosales.postulacionesapp.entity.GlassdoorEntity;

public interface GlassdoorService {

    GlassdoorEntity saveGlassdoor(GlassdoorEntity glassdoor);
    void deleteGlassdorById(Long id);
}
