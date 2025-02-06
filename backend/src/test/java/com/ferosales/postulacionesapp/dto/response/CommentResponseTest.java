package com.ferosales.postulacionesapp.dto.response;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CommentResponseTest {
    @Test
    void testCommentResponseProperties() {
        // Arrange
        List<String> opinions = Arrays.asList("Excellent workplace", "Good salary", "Friendly environment");

        // Act
        CommentResponse response = new CommentResponse();
        response.setCompany("TechCorp");
        response.setValue(4.5);
        response.setOpinions(opinions);
        response.setComment("Great place to work!");

        // Assert
        assertThat(response.getCompany()).isEqualTo("TechCorp");
        assertThat(response.getValue()).isEqualTo(4.5);
        assertThat(response.getOpinions()).isEqualTo(opinions);
        assertThat(response.getComment()).isEqualTo("Great place to work!");
    }

    @Test
    void testCommentResponseAllArgsConstructor() {
        // Arrange
        List<String> opinions = Arrays.asList("Excellent workplace", "Good salary", "Friendly environment");

        // Act
        CommentResponse response = new CommentResponse("TechCorp", 4.5, opinions, "Great place to work!");

        // Assert
        assertThat(response.getCompany()).isEqualTo("TechCorp");
        assertThat(response.getValue()).isEqualTo(4.5);
        assertThat(response.getOpinions()).isEqualTo(opinions);
        assertThat(response.getComment()).isEqualTo("Great place to work!");
    }

    @Test
    void testCommentResponseNoArgsConstructor() {
        // Act
        CommentResponse response = new CommentResponse();

        // Assert
        assertThat(response.getCompany()).isNull();
        assertThat(response.getValue()).isNull();
        assertThat(response.getOpinions()).isNull();
        assertThat(response.getComment()).isNull();
    }

    @Test
    void testCommentResponseToString() {
        // Arrange
        List<String> opinions = Arrays.asList("Excellent workplace", "Good salary", "Friendly environment");
        CommentResponse response = new CommentResponse("TechCorp", 4.5, opinions, "Great place to work!");

        // Act
        String toString = response.toString();
        String opinionsToString = response.getOpinions().toString();
        String value = response.getValue().toString();
        String opinionPersonal = response.getComment();

        // Assert
        assertThat(toString).contains(
                "company=TechCorp",
                value,
                opinionsToString,
                opinionPersonal
        );
        assertThat(opinionsToString).contains("Excellent workplace", "Good salary", "Friendly environment");
        assertThat(value).isEqualTo("4.5");
        assertThat(opinionPersonal).isEqualTo("Great place to work!");
    }

    @Test
    void testCommentResponseEquality() {
        // Arrange
        List<String> opinions = Arrays.asList("Excellent workplace", "Good salary", "Friendly environment");
        CommentResponse response1 = new CommentResponse("TechCorp", 4.5, opinions, "Great place to work!");
        CommentResponse response2 = new CommentResponse("TechCorp", 4.5, opinions, "Great place to work!");

        // Act & Assert
        assertThat(response1).isEqualTo(response2);
        assertThat(response1.hashCode()).isEqualTo(response2.hashCode());
    }
}