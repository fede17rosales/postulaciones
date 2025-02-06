package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.entity.CompanyEntity;
import com.ferosales.postulacionesapp.entity.OfferEntity;
import com.ferosales.postulacionesapp.entity.PublicationEntity;
import com.ferosales.postulacionesapp.repository.PublicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublicationServiceImplTest {
    @Mock
    private PublicationRepository publicationRepository;

    @InjectMocks
    private PublicationServiceImpl publicationService;

    private PublicationEntity publicationEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        publicationEntity = new PublicationEntity();
        publicationEntity.setId(1L);
        publicationEntity.setDatePublication(new Date());
        publicationEntity.setCompany(new CompanyEntity());
        publicationEntity.setOffer(new OfferEntity());
    }

    @Test
    public void testViewPublications() {
        List<PublicationEntity> publications = Collections.singletonList(publicationEntity);
        when(publicationRepository.findAllByOrderByIdDesc()).thenReturn(publications);

        List<PublicationEntity> result = publicationService.viewPublications();

        verify(publicationRepository, times(1)).findAllByOrderByIdDesc();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    public void testGetPublication() {
        when(publicationRepository.findById(1L)).thenReturn(Optional.of(publicationEntity));

        Optional<PublicationEntity> result = publicationService.getPublication(1L);

        verify(publicationRepository, times(1)).findById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    public void testSavePublication() {
        when(publicationRepository.save(any(PublicationEntity.class))).thenReturn(publicationEntity);

        publicationService.savePublication(publicationEntity);

        verify(publicationRepository, times(1)).save(any(PublicationEntity.class));
    }

    @Test
    public void testDeletePublication() {
        doNothing().when(publicationRepository).deleteById(1L);

        publicationService.deletePublication(1L);

        verify(publicationRepository, times(1)).deleteById(1L);
    }

}