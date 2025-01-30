package com.ferosales.postulacionesapp.controller;

import com.ferosales.postulacionesapp.dto.request.Postulation;
import com.ferosales.postulacionesapp.dto.response.PostulationResponse;
import com.ferosales.postulacionesapp.service.PostulationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PostulationControllerTest {
    @Mock
    private PostulationService postulationService;

    @InjectMocks
    private PostulationController postulationController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(postulationController).build();
    }

    @Test
    public void testListPostulations() throws Exception {
        PostulationResponse response = new PostulationResponse();
        List<String> respo = new ArrayList<>();
        respo.add("Test Responsibilities");
        respo.add("Test Responsibilities 2");
        respo.add("Test Responsibilities 3");
        response.setCompany("Test Company");
        response.setTitle("Test Title");
        response.setDescription("Test Description");
        response.setResponsibilities(respo);
        response.setRecruiter("Test Recruiter");
        response.setSalary("2000.00");
        response.setDate("2024-12-11");

        when(postulationService.viewPostulations()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/postulation"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testSavePostulation() throws Exception {
        Postulation postulacion = new Postulation();
        postulacion.setTitle("Test Title");
        postulacion.setDescription("Test Description");

        doNothing().when(postulationService).savePostulation(any(Postulation.class));

        mockMvc.perform(post("/postulation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"title\": \"Test Title\", \"description\": \"Test Description\" }"))
                .andExpect(status().isOk());

        verify(postulationService, times(1)).savePostulation(any(Postulation.class));
    }

    @Test
    public void testDeletePostulation() throws Exception {
        doNothing().when(postulationService).deletePostulation(1L);

        mockMvc.perform(delete("/postulation/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(postulationService, times(1)).deletePostulation(1L);
    }
}