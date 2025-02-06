package com.ferosales.postulacionesapp.service;

import com.ferosales.postulacionesapp.entity.CompanyEntity;

public interface CompanyService {

    CompanyEntity findCompanyByName(String name);
    CompanyEntity saveCompany(CompanyEntity empresa);
    void deleteCompanyById(Long id);

}
