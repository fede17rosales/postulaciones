package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.dto.request.Comment;
import com.ferosales.postulacionesapp.dto.response.CommentResponse;
import com.ferosales.postulacionesapp.entity.CompanyEntity;
import com.ferosales.postulacionesapp.entity.GlassdoorEntity;
import com.ferosales.postulacionesapp.entity.OpinionEntity;
import com.ferosales.postulacionesapp.repository.CompanyRepository;
import com.ferosales.postulacionesapp.repository.OpinionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class CommentServiceImplTest {
    @Mock
    private OpinionRepository opinionRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveComment() {
        // Arrange
        Comment comment = new Comment();
        comment.setCompany("TechCorp");
        comment.setComment("Great company!");

        CompanyEntity company = new CompanyEntity();
        company.setId(1);
        company.setName("TechCorp");

        OpinionEntity opinion1 = new OpinionEntity();
        opinion1.setId(1L);
        opinion1.setCompany(company);

        OpinionEntity opinion2 = new OpinionEntity();
        opinion2.setId(2L);
        opinion2.setCompany(company);

        List<OpinionEntity> opinions = Arrays.asList(opinion1, opinion2);

        when(companyRepository.findByName("TechCorp")).thenReturn(company);
        when(opinionRepository.findByCompanyId(1L)).thenReturn(opinions);

        // Act
        commentService.saveComment(comment);

        // Assert
        verify(companyRepository, times(1)).findByName("TechCorp");
        verify(opinionRepository, times(1)).findByCompanyId(1L);
        verify(opinionRepository, times(1)).save(opinion1);
        verify(opinionRepository, times(1)).save(opinion2);
        assertThat(opinion1.getPersonalOpinion()).isEqualTo("Great company!");
        assertThat(opinion2.getPersonalOpinion()).isEqualTo("Great company!");
    }

    @Test
    void testSaveNewComment() {
        // Arrange
        Comment comment = new Comment();
        comment.setCompany("TechCorp");
        comment.setComment("Great company!");

        CompanyEntity company = new CompanyEntity();
        company.setId(1);
        company.setName("TechCorp");

        when(companyRepository.findByName(comment.getCompany())).thenReturn(company);
        when(opinionRepository.findByCompanyId(1L)).thenReturn(Collections.emptyList());

        // Act
        commentService.saveComment(comment);

        // Assert
        verify(opinionRepository, times(1)).save(any(OpinionEntity.class));
    }

    @Test
    void testViewComments() {
        // Arrange
        CompanyEntity company = new CompanyEntity();
        company.setName("TechCorp");

        GlassdoorEntity glassdoor = new GlassdoorEntity();
        glassdoor.setValue(4.5);
        glassdoor.setOpinions(Arrays.asList("Excellent workplace", "Good salary"));

        OpinionEntity opinion1 = new OpinionEntity();
        opinion1.setCompany(company);
        opinion1.setGlassdoor(glassdoor);
        opinion1.setPersonalOpinion("Great environment");

        OpinionEntity opinion2 = new OpinionEntity();
        opinion2.setCompany(company);
        opinion2.setGlassdoor(glassdoor);
        opinion2.setPersonalOpinion(null);

        List<OpinionEntity> opinions = Arrays.asList(opinion1, opinion2);

        when(opinionRepository.findAllByOrderByIdDesc()).thenReturn(opinions);

        // Act
        List<CommentResponse> responses = commentService.viewComments();

        // Assert
        verify(opinionRepository, times(1)).findAllByOrderByIdDesc();
        assertThat(responses).isNotEmpty();
        assertThat(responses.size()).isEqualTo(1);

        CommentResponse response = responses.get(0);
        assertThat(response.getCompany()).isEqualTo("TechCorp");
        assertThat(response.getValue()).isEqualTo(4.5);
        assertThat(response.getOpinions()).containsExactly("Excellent workplace", "Good salary");
        assertThat(response.getComment()).isEqualTo("Great environment");
    }

    @Test
    void testViewCommentsWhenNoOpinions() {
        // Arrange
        when(opinionRepository.findAllByOrderByIdDesc()).thenReturn(Collections.emptyList());

        // Act
        List<CommentResponse> responses = commentService.viewComments();

        // Assert
        verify(opinionRepository, times(1)).findAllByOrderByIdDesc();
        assertThat(responses).isEmpty();
    }

    @Test
    void testViewCommentsWhenGlassdoorValueIsZero() {
        // Arrange
        CompanyEntity company = new CompanyEntity();
        company.setName("TechCorp");

        GlassdoorEntity glassdoor = new GlassdoorEntity();
        glassdoor.setValue(0);
        glassdoor.setOpinions(Arrays.asList("Bad management"));

        OpinionEntity opinion = new OpinionEntity();
        opinion.setCompany(company);
        opinion.setGlassdoor(glassdoor);
        opinion.setPersonalOpinion("Needs improvement");

        when(opinionRepository.findAllByOrderByIdDesc()).thenReturn(Collections.singletonList(opinion));

        // Act
        List<CommentResponse> responses = commentService.viewComments();

        // Assert
        verify(opinionRepository, times(1)).findAllByOrderByIdDesc();
        assertThat(responses).isEmpty();
    }

    @Test
    void testViewCommentsWhenGlassdoorOpinionsAreEmpty() {
        // Arrange
        CompanyEntity company = new CompanyEntity();
        company.setName("TechCorp");

        GlassdoorEntity glassdoor = new GlassdoorEntity();
        glassdoor.setValue(4.5);
        glassdoor.setOpinions(Collections.singletonList(""));

        OpinionEntity opinion = new OpinionEntity();
        opinion.setCompany(company);
        opinion.setGlassdoor(glassdoor);
        opinion.setPersonalOpinion("Good company");

        when(opinionRepository.findAllByOrderByIdDesc()).thenReturn(Collections.singletonList(opinion));

        // Act
        List<CommentResponse> responses = commentService.viewComments();

        // Assert
        verify(opinionRepository, times(1)).findAllByOrderByIdDesc();
        assertThat(responses).isNotEmpty();
        assertThat(responses.size()).isEqualTo(1);
        assertThat(responses.get(0).getOpinions()).containsExactly("No hay opiniones");
    }
}