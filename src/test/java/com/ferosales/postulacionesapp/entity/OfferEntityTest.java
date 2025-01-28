package com.ferosales.postulacionesapp.entity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OfferEntityTest {
    @Test
    void testOfferEntityProperties() {
        // Arrange
        OfferEntity offer = new OfferEntity();
        List<String> responsibilities = Arrays.asList("Develop features", "Fix bugs", "Write documentation");

        // Act
        offer.setId(1L);
        offer.setTitle("Software Engineer");
        offer.setDescription("A great opportunity to work in tech");
        offer.setResponsibilities(responsibilities);
        offer.setRecruiter("John Doe");
        offer.setSalary(120000.0);
        offer.setBenefits("Health insurance, 401k");
        offer.setEnglish(true);
        offer.setStatus("Open");

        // Assert
        assertThat(offer.getId()).isEqualTo(1L);
        assertThat(offer.getTitle()).isEqualTo("Software Engineer");
        assertThat(offer.getDescription()).isEqualTo("A great opportunity to work in tech");
        assertThat(offer.getResponsibilities()).isEqualTo(responsibilities);
        assertThat(offer.getRecruiter()).isEqualTo("John Doe");
        assertThat(offer.getSalary()).isEqualTo(120000.0);
        assertThat(offer.getBenefits()).isEqualTo("Health insurance, 401k");
        assertThat(offer.getEnglish()).isTrue();
        assertThat(offer.getStatus()).isEqualTo("Open");
    }

    @Test
    void testOfferEntityEquality() {
        // Arrange
        OfferEntity offer1 = new OfferEntity();
        offer1.setId(1L);
        offer1.setTitle("Software Engineer");
        offer1.setDescription("A great opportunity to work in tech");
        offer1.setResponsibilities(Arrays.asList("Develop features", "Fix bugs", "Write documentation"));
        offer1.setRecruiter("John Doe");
        offer1.setSalary(120000.0);
        offer1.setBenefits("Health insurance, 401k");
        offer1.setEnglish(true);
        offer1.setStatus("Open");

        OfferEntity offer2 = new OfferEntity();
        offer2.setId(1L);
        offer2.setTitle("Software Engineer");
        offer2.setDescription("A great opportunity to work in tech");
        offer2.setResponsibilities(Arrays.asList("Develop features", "Fix bugs", "Write documentation"));
        offer2.setRecruiter("John Doe");
        offer2.setSalary(120000.0);
        offer2.setBenefits("Health insurance, 401k");
        offer2.setEnglish(true);
        offer2.setStatus("Open");

        // Act & Assert
        assertThat(offer1).isEqualTo(offer2);
        assertThat(offer1.hashCode()).isEqualTo(offer2.hashCode());
    }

    @Test
    void testOfferEntityToString() {
        // Arrange
        OfferEntity offer = new OfferEntity();
        offer.setId(1L);
        offer.setTitle("Software Engineer");
        offer.setDescription("A great opportunity to work in tech");
        offer.setResponsibilities(Arrays.asList("Develop features", "Fix bugs", "Write documentation"));
        offer.setRecruiter("John Doe");
        offer.setSalary(120000.0);
        offer.setBenefits("Health insurance, 401k");
        offer.setEnglish(true);
        offer.setStatus("Open");

        // Act
        String toString = offer.toString();

        // Assert
        assertThat(toString).contains(
                "id=1",
                "title=Software Engineer",
                "description=A great opportunity to work in tech",
                "responsibilities=[Develop features, Fix bugs, Write documentation]",
                "recruiter=John Doe",
                "salary=120000.0",
                "benefits=Health insurance, 401k",
                "english=true",
                "status=Open"
        );
    }
}