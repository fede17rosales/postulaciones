package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.entity.OfferEntity;
import com.ferosales.postulacionesapp.repository.OfferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
class OfferServiceImplTest {
    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private OfferServiceImpl offerService;

    private OfferEntity offer;

    @BeforeEach
    public void setUp() {
        offer = new OfferEntity();
        offer.setId(1L);
        offer.setTitle("Oferta de Trabajo");
        offer.setDescription("Descripción de la oferta");
    }

    @Test
    public void testSaveOffer() {
        when(offerRepository.save(any(OfferEntity.class))).thenReturn(offer);

        OfferEntity savedOffer = offerService.saveOffer(offer);

        verify(offerRepository, times(1)).save(any(OfferEntity.class));
        assert(savedOffer != null);
        assert(savedOffer.getId().equals(1L));
        assert(savedOffer.getTitle().equals("Oferta de Trabajo"));
    }

    @Test
    public void testDeleteOfferById() {
        offerService.deleteOfferById(1L);

        verify(offerRepository, times(1)).deleteById(1L);
    }

}