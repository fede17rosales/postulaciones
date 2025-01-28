package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.dto.request.Postulation;
import com.ferosales.postulacionesapp.dto.response.PostulationResponse;
import com.ferosales.postulacionesapp.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class PostulationServiceImplTest {
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

    @InjectMocks
    private PostulationServiceImpl postulationService;

    private Postulation postulation;
    private CompanyEntity companyEntity;
    private OfferEntity offerEntity;
    private GlassdoorEntity glassdoorEntity;
    private PublicationEntity publicationEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        List<String> r = new ArrayList<>();
        r.add("respo 1");
        r.add("respo 2");
        r.add("respo 3");
        postulation = new Postulation();
        postulation.setTitle("Developer");
        postulation.setDescription("Full-stack Developer position");
        postulation.setResponsibilities(r);
        postulation.setRecruiter("HR");
        postulation.setSalary(500.00);
        postulation.setBenefits("Health Insurance");
        postulation.setEnglish(true);
        postulation.setCompany("Tech Corp");

        companyEntity = new CompanyEntity();
        companyEntity.setName("Tech Corp");

        offerEntity = new OfferEntity();
        offerEntity.setTitle("Developer");
        offerEntity.setDescription("Full-stack Developer position");
        offerEntity.setSalary(100.00);

        glassdoorEntity = new GlassdoorEntity();
        glassdoorEntity.setSalary(50000);

        publicationEntity = new PublicationEntity();
        publicationEntity.setCompany(companyEntity);
        publicationEntity.setOffer(offerEntity);
        publicationEntity.setDatePublication(new Date("2025/10/10"));
    }

    @Test
    public void testViewPostulations() {

        when(publicationService.viewPublications()).thenReturn(Collections.singletonList(publicationEntity));

        List<PostulationResponse> responses = postulationService.viewPostulations();

        verify(publicationService, times(1)).viewPublications();
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Tech Corp", responses.get(0).getCompany());
        assertEquals("Developer", responses.get(0).getTitle());
    }

    @Test
    public void testSavePostulation() {
        when(offerService.saveOffer(any(OfferEntity.class))).thenReturn(offerEntity);
        when(glassdoorService.saveGlassdoor(any(GlassdoorEntity.class))).thenReturn(glassdoorEntity);
        when(companyService.findCompanyByName("Tech Corp")).thenReturn(companyEntity);
        when(opinionService.save(any(OpinionEntity.class))).thenReturn(new OpinionEntity());

        postulationService.savePostulation(postulation);

        verify(offerService, times(1)).saveOffer(any(OfferEntity.class));
        verify(glassdoorService, times(1)).saveGlassdoor(any(GlassdoorEntity.class));
        verify(companyService, times(1)).findCompanyByName("Tech Corp");
        verify(publicationService, times(1)).savePublication(any(PublicationEntity.class));
        verify(opinionService, times(1)).save(any(OpinionEntity.class));
    }

    @Test
    public void testDeletePostulation() {
        when(publicationService.getPublication(1L)).thenReturn(Optional.of(publicationEntity));

        postulationService.deletePostulation(1L);

        verify(publicationService, times(1)).getPublication(1L);
        verify(publicationService, times(1)).deletePublication(1L);
        verify(offerService, times(1)).deleteOfferById(publicationEntity.getOffer().getId());
    }

}