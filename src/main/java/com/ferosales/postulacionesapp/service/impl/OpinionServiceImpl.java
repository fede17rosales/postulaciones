package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.entity.OpinionEntity;
import com.ferosales.postulacionesapp.repository.OpinionRepository;
import com.ferosales.postulacionesapp.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    private OpinionRepository repository;

    @Override
    public OpinionEntity save(OpinionEntity opinionEntity) {

        return repository.save(opinionEntity);
    }

}
