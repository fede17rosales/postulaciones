package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.dto.request.Comment;
import com.ferosales.postulacionesapp.dto.response.CommentResponse;
import com.ferosales.postulacionesapp.entity.CompanyEntity;
import com.ferosales.postulacionesapp.entity.GlassdoorEntity;
import com.ferosales.postulacionesapp.entity.OpinionEntity;
import com.ferosales.postulacionesapp.repository.OpinionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class CommentServiceImplTest {
    @Mock
    private OpinionRepository repository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveComment() {
        // Arrange
        Long companyId = 1L;
        Comment comment = new Comment();
        comment.setComment("Great company!");

        OpinionEntity opinion1 = new OpinionEntity();
        opinion1.setId(1L);

        OpinionEntity opinion2 = new OpinionEntity();
        opinion2.setId(2L);

        List<OpinionEntity> opinions = Arrays.asList(opinion1, opinion2);

        when(repository.findByCompanyId(companyId)).thenReturn(opinions);

        // Act
        commentService.saveComment(companyId, comment);

        // Assert
        verify(repository, times(1)).findByCompanyId(companyId);
        verify(repository, times(1)).save(opinion1);
        verify(repository, times(1)).save(opinion2);
        assertThat(opinion1.getPersonalOpinion()).isEqualTo("Great company!");
        assertThat(opinion2.getPersonalOpinion()).isEqualTo("Great company!");
    }

    @Test
    void testViewComments() {
        // Arrange
        CompanyEntity company = new CompanyEntity();
        List<String> opinions = new ArrayList<>();
        opinions.add("Excellent workplace");
        opinions.add("Good salary");
        company.setName("TechCorp");

        GlassdoorEntity glassdoor = new GlassdoorEntity();
        glassdoor.setValue(4.5);
        glassdoor.setOpinions(opinions);

        OpinionEntity opinion1 = new OpinionEntity();
        opinion1.setCompany(company);
        opinion1.setGlassdoor(glassdoor);
        opinion1.setPersonalOpinion("Great environment");

        OpinionEntity opinion2 = new OpinionEntity();
        opinion2.setCompany(company);
        opinion2.setGlassdoor(glassdoor);
        opinion2.setPersonalOpinion(null); // This opinion should be skipped

        List<OpinionEntity> o = Arrays.asList(opinion1, opinion2);

        when(repository.findAll()).thenReturn(o);

        // Act
        List<CommentResponse> responses = commentService.viewComments();

        // Assert
        verify(repository, times(1)).findAll();

        CommentResponse response = responses.get(0);
        assertThat(response.getCompany()).isEqualTo("TechCorp");
        assertThat(response.getValue()).isEqualTo(4.5);
        assertThat(response.getOpinions()).isNotNull();
        assertThat(response.getComment()).isEqualTo("Great environment");
    }

    @Test
    void testViewCommentsWhenNoOpinions() {
        // Arrange
        when(repository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<CommentResponse> responses = commentService.viewComments();

        // Assert
        verify(repository, times(1)).findAll();
        assertThat(responses).isNotNull();
    }
}