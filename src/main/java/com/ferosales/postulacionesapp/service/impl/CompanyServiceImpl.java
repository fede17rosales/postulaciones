package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.entity.CompanyEntity;
import com.ferosales.postulacionesapp.repository.CompanyRepository;
import com.ferosales.postulacionesapp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository repository;

    @Override
    public CompanyEntity findCompanyByName(String name) {
        return repository.findByName(name);
    }

    @Override
    @Transactional
    public CompanyEntity saveCompany(CompanyEntity empresa) {
        return repository.save(empresa);
    }

    @Override
    @Transactional
    public void deleteCompanyById(Long id) {
        repository.deleteById(id);
    }
}
