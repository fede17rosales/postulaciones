package com.ferosales.postulacionesapp.entity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GlassdoorEntityTest {
    @Test
    void testGlassdoorEntityProperties() {
        // Arrange
        GlassdoorEntity glassdoor = new GlassdoorEntity();

        List<String> opinions = Arrays.asList("Great place to work", "Needs better management");

        // Act
        glassdoor.setId(1);
        glassdoor.setOpinions(opinions);
        glassdoor.setValue(4.5);
        glassdoor.setSalary(75000);

        // Assert
        assertThat(glassdoor.getId()).isEqualTo(1);
        assertThat(glassdoor.getOpinions()).isEqualTo(opinions);
        assertThat(glassdoor.getValue()).isEqualTo(4.5);
        assertThat(glassdoor.getSalary()).isEqualTo(75000);
    }

    @Test
    void testGlassdoorEntityEquality() {
        // Arrange
        GlassdoorEntity glassdoor1 = new GlassdoorEntity();
        glassdoor1.setId(1);
        glassdoor1.setOpinions(Arrays.asList("Great place to work", "Needs better management"));
        glassdoor1.setValue(4.5);
        glassdoor1.setSalary(75000);

        GlassdoorEntity glassdoor2 = new GlassdoorEntity();
        glassdoor2.setId(1);
        glassdoor2.setOpinions(Arrays.asList("Great place to work", "Needs better management"));
        glassdoor2.setValue(4.5);
        glassdoor2.setSalary(75000);

        // Act & Assert
        assertThat(glassdoor1).isEqualTo(glassdoor2);
        assertThat(glassdoor1.hashCode()).isEqualTo(glassdoor2.hashCode());
    }

    @Test
    void testGlassdoorEntityToString() {
        // Arrange
        GlassdoorEntity glassdoor = new GlassdoorEntity();
        glassdoor.setId(1);
        glassdoor.setOpinions(Arrays.asList("Great place to work", "Needs better management"));
        glassdoor.setValue(4.5);
        glassdoor.setSalary(75000);

        // Act
        String toString = glassdoor.toString();

        // Assert
        assertThat(toString).contains("id=1", "opinions=[Great place to work, Needs better management]", "value=4.5", "salary=75000");
    }
}