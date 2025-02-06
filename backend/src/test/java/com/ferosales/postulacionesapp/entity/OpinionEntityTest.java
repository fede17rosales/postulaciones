package com.ferosales.postulacionesapp.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
class OpinionEntityTest {
    @Test
        void testOpinionEntityProperties() {
            // Arrange
            OpinionEntity opinion = new OpinionEntity();
            CompanyEntity company = new CompanyEntity();
            company.setId(1);
            company.setName("TechCorp");

            GlassdoorEntity glassdoor = new GlassdoorEntity();
            glassdoor.setId(1);

            // Act
            opinion.setId(1L);
            opinion.setCompany(company);
            opinion.setGlassdoor(glassdoor);
            opinion.setPersonalOpinion("Great company to work for!");

            // Assert
            assertThat(opinion.getId()).isEqualTo(1L);
            assertThat(opinion.getCompany()).isEqualTo(company);
            assertThat(opinion.getGlassdoor()).isEqualTo(glassdoor);
            assertThat(opinion.getPersonalOpinion()).isEqualTo("Great company to work for!");
        }

        @Test
        void testOpinionEntityEquality() {
            // Arrange
            CompanyEntity company = new CompanyEntity();
            company.setId(1);

            GlassdoorEntity glassdoor = new GlassdoorEntity();
            glassdoor.setId(1);

            OpinionEntity opinion1 = new OpinionEntity();
            opinion1.setId(1L);
            opinion1.setCompany(company);
            opinion1.setGlassdoor(glassdoor);
            opinion1.setPersonalOpinion("Great company to work for!");

            OpinionEntity opinion2 = new OpinionEntity();
            opinion2.setId(1L);
            opinion2.setCompany(company);
            opinion2.setGlassdoor(glassdoor);
            opinion2.setPersonalOpinion("Great company to work for!");

            // Act & Assert
            assertThat(opinion1).isEqualTo(opinion2);
            assertThat(opinion1.hashCode()).isEqualTo(opinion2.hashCode());
        }

        @Test
        void testOpinionEntityToString() {
            // Arrange
            CompanyEntity company = new CompanyEntity();
            company.setId(1);
            company.setName("TechCorp");

            GlassdoorEntity glassdoor = new GlassdoorEntity();
            glassdoor.setId(1);

            OpinionEntity opinion = new OpinionEntity();
            opinion.setId(1L);
            opinion.setCompany(company);
            opinion.setGlassdoor(glassdoor);
            opinion.setPersonalOpinion("Great company to work for!");

            // Act
            String glassdoorOpinionString = opinion.getGlassdoor().toString();
            String companyOpinionString = opinion.getCompany().toString();
            String opinionToString = opinion.toString();

            // Assert
            assertThat(opinionToString).contains(
                    "id=1",
                    companyOpinionString,
                    glassdoorOpinionString,
                    "personalOpinion=Great company to work for!"
            );
            assertThat(companyOpinionString).contains("id=1", "name=TechCorp");
            assertThat(glassdoorOpinionString).contains("id=1");
    }

}