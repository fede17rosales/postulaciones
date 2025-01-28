package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.entity.CompanyEntity;
import com.ferosales.postulacionesapp.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.Mockito.*;

@SpringBootTest
class CompanyServiceImplTest {

    @Mock
    private CompanyRepository repository;

    @InjectMocks
    private CompanyServiceImpl companyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindCompanyByName() {
        // Arrange
        String companyName = "TechCorp";
        CompanyEntity company = new CompanyEntity();
        company.setName(companyName);

        when(repository.findByName(companyName)).thenReturn(company);

        // Act
        CompanyEntity result = companyService.findCompanyByName(companyName);

        // Assert
        verify(repository, times(1)).findByName(companyName);
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(companyName);
    }

    @Test
    void testSaveCompany() {
        // Arrange
        CompanyEntity company = new CompanyEntity();
        company.setName("NewTech");
        company.setAntique(10);

        when(repository.save(company)).thenReturn(company);

        // Act
        CompanyEntity result = companyService.saveCompany(company);

        // Assert
        verify(repository, times(1)).save(company);
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("NewTech");
        assertThat(result.getAntique()).isEqualTo(10);
    }

    @Test
    void testDeleteCompanyById() {
        // Arrange
        Long companyId = 1L;
        doNothing().when(repository).deleteById(companyId);

        // Act
        companyService.deleteCompanyById(companyId);

        // Assert
        verify(repository, times(1)).deleteById(companyId);
    }
}