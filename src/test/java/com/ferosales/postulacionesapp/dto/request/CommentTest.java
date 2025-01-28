package com.ferosales.postulacionesapp.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @Test
    void getComment() {
        Comment comment = new Comment();
        comment.setComment("Hola");
        assertEquals("Hola", comment.getComment());
    }

    @Test
    void setComment() {
        Comment comment = new Comment();
        comment.setComment("Hola");
        assertEquals("Hola", comment.getComment());
    }

}