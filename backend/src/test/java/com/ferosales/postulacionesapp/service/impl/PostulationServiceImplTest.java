package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.dto.request.Postulation;
import com.ferosales.postulacionesapp.dto.response.PostulationResponse;
import com.ferosales.postulacionesapp.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostulationServiceImplTest {

    @Mock
    private OfferServiceImpl offerService;

    @Mock
    private CompanyServiceImpl companyService;

    @Mock
    private GlassdoorServiceImpl glassdoorService;

    @Mock
    private PublicationServiceImpl publicationService;

    @Mock
    private OpinionServiceImpl opinionService;

    @Mock
    private ResponsibilityServiceImpl responsibilityService;

    @Mock
    private TaskServiceImpl taskService;

    @InjectMocks
    private PostulationServiceImpl postulationService;

    private Postulation postulation;
    private CompanyEntity companyEntity;
    private OfferEntity offerEntity;
    private PublicationEntity publicationEntity;
    private TaskEntity taskEntity;

    @BeforeEach
    void setUp() {
        postulation = new Postulation();
        postulation.setCompany("Test Company");
        postulation.setTitle("Software Engineer");
        postulation.setResponsibilities(List.of("Coding", "Testing"));
        postulation.setSalary(5000.00);

        companyEntity = new CompanyEntity();
        companyEntity.setName("Test Company");

        offerEntity = new OfferEntity();
        offerEntity.setId(1L);
        offerEntity.setTitle("Software Engineer");

        publicationEntity = new PublicationEntity();
        publicationEntity.setId(1L);
        publicationEntity.setCompany(companyEntity);
        publicationEntity.setOffer(offerEntity);
        publicationEntity.setDatePublication(new Date());

        taskEntity = new TaskEntity();
        taskEntity.setId(1L);
        ResponsibilityEntity responsibility = new ResponsibilityEntity();
        responsibility.setDescription("Coding");
        taskEntity.setResponsibility(responsibility);
        taskEntity.setOffer(offerEntity);
    }

    @Test
    void testViewPostulations() {
        when(publicationService.viewPublications()).thenReturn(List.of(publicationEntity));
        when(taskService.viewTasks()).thenReturn(List.of(taskEntity));

        List<PostulationResponse> responses = postulationService.viewPostulations();

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Software Engineer", responses.get(0).getTitle());
    }

    @Test
    void testSavePostulation() {
        when(companyService.findCompanyByName("Test Company")).thenReturn(null);
        when(companyService.saveCompany(any())).thenReturn(companyEntity);
        when(offerService.saveOffer(any())).thenReturn(offerEntity);
        when(glassdoorService.saveGlassdoor(any())).thenReturn(new GlassdoorEntity());
        when(opinionService.save(any())).thenReturn(null);

        assertDoesNotThrow(() -> postulationService.savePostulation(postulation));

        verify(companyService, times(1)).saveCompany(any());
        verify(offerService, times(1)).saveOffer(any());
    }

    @Test
    void testDeletePostulation() {
        when(publicationService.getPublication(1L)).thenReturn(Optional.of(publicationEntity));
        when(taskService.findTaskByOfferId(1L)).thenReturn(List.of(taskEntity));
        when(offerService.findOfferById(anyLong())).thenReturn(Optional.of(offerEntity));

        assertDoesNotThrow(() -> postulationService.deletePostulation(1L));

        verify(publicationService, times(1)).deletePublication(anyLong());
        verify(taskService, times(1)).deleteById(anyLong());
        verify(offerService, times(1)).deleteOfferById(anyLong());
    }

    @Test
    void testSetSalaryCoin() {
        assertEquals("No especificado", postulationService.setSalaryCoin(null));
        assertEquals("$2000.0 USD", postulationService.setSalaryCoin(2000.0));
        assertEquals("$3000.0 USD", postulationService.setSalaryCoin(3000.0));
        assertEquals("$4000.0 ARS", postulationService.setSalaryCoin(4000.0));
    }
}
