package com.ferosales.postulacionesapp.controller;

import com.ferosales.postulacionesapp.dto.request.Comment;
import com.ferosales.postulacionesapp.dto.response.CommentResponse;
import com.ferosales.postulacionesapp.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CommentControllerTest {
    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    @Test
    public void testListComments() throws Exception {
        CommentResponse response = new CommentResponse();
        response.setComment("Test Comment");

        when(commentService.viewComments()).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/comment"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testSaveComment() throws Exception {
        Comment comment = new Comment();
        comment.setComment("Test Comment");

        doNothing().when(commentService).saveComment(any(Comment.class));

        mockMvc.perform(post("/comment", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"company\": \"Company\",\"comment\": \"Test Comment\" }"))
                .andExpect(status().isOk());

        verify(commentService, times(1)).saveComment( any(Comment.class));
    }
}