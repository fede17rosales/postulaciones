package com.ferosales.postulacionesapp.controller;

import com.ferosales.postulacionesapp.dto.request.Comment;
import com.ferosales.postulacionesapp.dto.response.CommentResponse;
import com.ferosales.postulacionesapp.entity.OpinionEntity;
import com.ferosales.postulacionesapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/comment")
    public ResponseEntity<List<CommentResponse>> listComments() {
        return ResponseEntity.ok(commentService.viewComments());
    }

    @PostMapping("/comment/{id}")
    public ResponseEntity<?> saveComment(@PathVariable("id") Long id, @RequestBody Comment comment) {
        commentService.saveComment(id,comment);
        return ResponseEntity.ok().build();
    }

}
