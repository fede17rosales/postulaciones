package com.ferosales.postulacionesapp.dto.response;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PostulationResponseTest {
    @Test
    void testPostulationResponseProperties() {
        // Arrange
        List<String> responsibilities = Arrays.asList("Develop features", "Fix bugs", "Write tests");

        // Act
        PostulationResponse response = new PostulationResponse();
        response.setCompany("TechCorp");
        response.setTitle("Software Engineer");
        response.setDescription("Develop and maintain software solutions.");
        response.setResponsibilities(responsibilities);
        response.setSalary("75000.0");
        response.setRecruiter("John Doe");
        response.setDate("2024-12-01");

        // Assert
        assertThat(response.getCompany()).isEqualTo("TechCorp");
        assertThat(response.getTitle()).isEqualTo("Software Engineer");
        assertThat(response.getDescription()).isEqualTo("Develop and maintain software solutions.");
        assertThat(response.getResponsibilities()).isEqualTo(responsibilities);
        assertThat(response.getSalary()).isEqualTo("75000.0");
        assertThat(response.getRecruiter()).isEqualTo("John Doe");
        assertThat(response.getDate()).isEqualTo("2024-12-01");
    }

    @Test
    void testPostulationResponseAllArgsConstructor() {
        // Arrange
        List<String> responsibilities = Arrays.asList("Develop features", "Fix bugs", "Write tests");

        // Act
        PostulationResponse response = new PostulationResponse(
                1L,
                "TechCorp",
                "Software Engineer",
                "Develop and maintain software solutions.",
                responsibilities,
                "75000.0",
                "John Doe",
                "2024-12-01"
        );

        // Assert
        assertThat(response.getCompany()).isEqualTo("TechCorp");
        assertThat(response.getTitle()).isEqualTo("Software Engineer");
        assertThat(response.getDescription()).isEqualTo("Develop and maintain software solutions.");
        assertThat(response.getResponsibilities()).isEqualTo(responsibilities);
        assertThat(response.getSalary()).isEqualTo("75000.0");
        assertThat(response.getRecruiter()).isEqualTo("John Doe");
        assertThat(response.getDate()).isEqualTo("2024-12-01");
    }

    @Test
    void testPostulationResponseNoArgsConstructor() {
        // Act
        PostulationResponse response = new PostulationResponse();

        // Assert
        assertThat(response.getCompany()).isNull();
        assertThat(response.getTitle()).isNull();
        assertThat(response.getDescription()).isNull();
        assertThat(response.getResponsibilities()).isNull();
        assertThat(response.getSalary()).isNull();
        assertThat(response.getRecruiter()).isNull();
        assertThat(response.getDate()).isNull();
    }

    @Test
    void testPostulationResponseToString() {
        // Arrange
        List<String> responsibilities = Arrays.asList("Develop features", "Fix bugs", "Write tests");
        PostulationResponse response = new PostulationResponse(
                1L,
                "TechCorp",
                "Software Engineer",
                "Develop and maintain software solutions.",
                responsibilities,
                "75000.0",
                "John Doe",
                "2024-12-01"
        );

        // Act
        String toString = response.toString();

        // Assert
        assertThat(toString).isNotEmpty();
    }

    @Test
    void testPostulationResponseEquality() {
        // Arrange
        List<String> responsibilities = Arrays.asList("Develop features", "Fix bugs", "Write tests");
        PostulationResponse response1 = new PostulationResponse(
                1L,
                "TechCorp",
                "Software Engineer",
                "Develop and maintain software solutions.",
                responsibilities,
                "75000.0",
                "John Doe",
                "2024-12-01"
        );
        PostulationResponse response2 = new PostulationResponse(
                1L,
                "TechCorp",
                "Software Engineer",
                "Develop and maintain software solutions.",
                responsibilities,
                "75000.0",
                "John Doe",
                "2024-12-01"
        );

        // Act & Assert
        assertThat(response1).isEqualTo(response2);
        assertThat(response1.hashCode()).isEqualTo(response2.hashCode());
    }
}