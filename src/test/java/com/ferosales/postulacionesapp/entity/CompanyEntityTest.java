package com.ferosales.postulacionesapp.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyEntityTest {
    @Test
    void testCompanyEntityProperties() {
        // Arrange
        CompanyEntity company = new CompanyEntity();

        // Act
        company.setId(1);
        company.setName("TechCorp");
        company.setAntique(10);
        company.setType("Software");
        company.setEmployees(200);

        // Assert
        assertThat(company.getId()).isEqualTo(1);
        assertThat(company.getName()).isEqualTo("TechCorp");
        assertThat(company.getAntique()).isEqualTo(10);
        assertThat(company.getType()).isEqualTo("Software");
        assertThat(company.getEmployees()).isEqualTo(200);
    }

    @Test
    void testCompanyEntityEquality() {
        // Arrange
        CompanyEntity company1 = new CompanyEntity();
        company1.setId(1);
        company1.setName("TechCorp");
        company1.setAntique(10);
        company1.setType("Software");
        company1.setEmployees(200);

        CompanyEntity company2 = new CompanyEntity();
        company2.setId(1);
        company2.setName("TechCorp");
        company2.setAntique(10);
        company2.setType("Software");
        company2.setEmployees(200);

        // Act & Assert
        assertThat(company1).isEqualTo(company2);
        assertThat(company1.hashCode()).isEqualTo(company2.hashCode());
    }

    @Test
    void testCompanyEntityToString() {
        // Arrange
        CompanyEntity company = new CompanyEntity();
        company.setId(1);
        company.setName("TechCorp");
        company.setAntique(10);
        company.setType("Software");
        company.setEmployees(200);

        // Act
        String toString = company.toString();

        // Assert
        assertThat(toString).contains("id=1", "name=TechCorp", "antique=10", "type=Software", "employees=200");
    }
}