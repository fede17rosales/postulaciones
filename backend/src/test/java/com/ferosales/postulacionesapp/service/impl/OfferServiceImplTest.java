package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.entity.OfferEntity;
import com.ferosales.postulacionesapp.repository.OfferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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

    @Test
    public void testFindOfferById() {
        when(offerRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(offer));

        var foundOffer = offerService.findOfferById(1L);

        verify(offerRepository, times(1)).findById(1L);
        assert(foundOffer.isPresent());
        assert(foundOffer.get().getId().equals(1L));
        assert(foundOffer.get().getTitle().equals("Oferta de Trabajo"));
    }

}