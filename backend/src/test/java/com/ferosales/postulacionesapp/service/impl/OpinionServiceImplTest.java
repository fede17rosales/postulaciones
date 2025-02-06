package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.entity.OpinionEntity;
import com.ferosales.postulacionesapp.repository.OpinionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OpinionServiceImplTest {
    @Mock
    private OpinionRepository opinionRepository;

    @InjectMocks
    private OpinionServiceImpl opinionService;

    private OpinionEntity opinionEntity;

    @BeforeEach
    public void setUp() {
        opinionEntity = new OpinionEntity();
        opinionEntity.setId(1L);
        opinionEntity.setPersonalOpinion("Muy buena oferta");
    }

    @Test
    public void testSaveOpinion() {
        when(opinionRepository.save(any(OpinionEntity.class))).thenReturn(opinionEntity);

        OpinionEntity savedOpinion = opinionService.save(opinionEntity);

        verify(opinionRepository, times(1)).save(any(OpinionEntity.class));
        assertNotNull(savedOpinion);
        assertEquals(1L, savedOpinion.getId());
        assertEquals("Muy buena oferta", savedOpinion.getPersonalOpinion());
    }

}