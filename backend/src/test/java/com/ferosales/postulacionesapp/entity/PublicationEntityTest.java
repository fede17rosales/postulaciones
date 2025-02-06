package com.ferosales.postulacionesapp.entity;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class PublicationEntityTest {
    @Test
    void testPublicationEntityProperties() throws ParseException {
        // Arrange
        PublicationEntity publication = new PublicationEntity();
        OfferEntity offer = new OfferEntity();
        offer.setId(1L);
        offer.setTitle("Software Engineer");

        CompanyEntity company = new CompanyEntity();
        company.setId(1);
        company.setName("TechCorp");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date publicationDate = dateFormat.parse("01-01-2024");

        // Act
        publication.setId(1L);
        publication.setOffer(offer);
        publication.setCompany(company);
        publication.setDatePublication(publicationDate);

        // Assert
        assertThat(publication.getId()).isEqualTo(1L);
        assertThat(publication.getOffer()).isEqualTo(offer);
        assertThat(publication.getCompany()).isEqualTo(company);
        assertThat(publication.getDatePublication()).isEqualTo(publicationDate);
    }

    @Test
    void testPublicationEntityEquality() throws Exception {
        // Arrange
        OfferEntity offer = new OfferEntity();
        offer.setId(1L);

        CompanyEntity company = new CompanyEntity();
        company.setId(1);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date publicationDate = dateFormat.parse("01-01-2024");

        PublicationEntity publication1 = new PublicationEntity();
        publication1.setId(1L);
        publication1.setOffer(offer);
        publication1.setCompany(company);
        publication1.setDatePublication(publicationDate);

        PublicationEntity publication2 = new PublicationEntity();
        publication2.setId(1L);
        publication2.setOffer(offer);
        publication2.setCompany(company);
        publication2.setDatePublication(publicationDate);

        // Act & Assert
        assertThat(publication1).isEqualTo(publication2);
        assertThat(publication1.hashCode()).isEqualTo(publication2.hashCode());
    }

    @Test
    void testPublicationEntityToString() throws Exception {
        // Arrange
        OfferEntity offer = new OfferEntity();
        offer.setId(1L);
        offer.setTitle("Software Engineer");

        CompanyEntity company = new CompanyEntity();
        company.setId(1);
        company.setName("TechCorp");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date publicationDate = dateFormat.parse("01-01-2024");

        PublicationEntity publication = new PublicationEntity();
        publication.setId(1L);
        publication.setOffer(offer);
        publication.setCompany(company);
        publication.setDatePublication(publicationDate);

        // Act
        String toString = publication.toString();
        String offerToString = publication.getOffer().toString();
        String companyToString = publication.getCompany().toString();

        // Assert
        assertThat(toString).contains(
                "id=1",
                offerToString,
                companyToString,
                "datePublication=Mon Jan 01"
        );
        assertThat(offerToString).contains("id=1", "title=Software Engineer");
        assertThat(companyToString).contains("id=1", "name=TechCorp");
    }
}