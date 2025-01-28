package com.ferosales.postulacionesapp.service;

import com.ferosales.postulacionesapp.entity.CompanyEntity;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    CompanyEntity findCompanyByName(String name);
    CompanyEntity saveCompany(CompanyEntity empresa);
    void deleteCompanyById(Long id);

}
