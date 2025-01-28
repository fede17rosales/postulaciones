package com.ferosales.postulacionesapp.controller;


import com.ferosales.postulacionesapp.dto.request.Postulation;
import com.ferosales.postulacionesapp.dto.response.PostulationResponse;
import com.ferosales.postulacionesapp.service.PostulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class PostulationController {

    @Autowired
    private PostulationService postulationService;

    @GetMapping("/postulation")
    public ResponseEntity<List<PostulationResponse>> listPostulations() {
        return ResponseEntity.ok(postulationService.viewPostulations());
    }

    @PostMapping("/postulation")
    public ResponseEntity<?> savePostulation(@RequestBody Postulation postulacion) {
        postulationService.savePostulation(postulacion);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/postulation/{id}")
    public ResponseEntity<?> deletePostulation(@PathVariable("id") Long id) {
        postulationService.deletePostulation(id);
        return ResponseEntity.noContent().build();
    }

}
