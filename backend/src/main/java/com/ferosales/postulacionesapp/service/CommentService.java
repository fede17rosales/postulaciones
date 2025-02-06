package com.ferosales.postulacionesapp.service;

import com.ferosales.postulacionesapp.dto.request.Comment;
import com.ferosales.postulacionesapp.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {
    void saveComment(Comment comment);
    List<CommentResponse> viewComments();

}
