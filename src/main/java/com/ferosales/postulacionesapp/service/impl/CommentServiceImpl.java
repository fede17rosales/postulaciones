package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.dto.request.Comment;
import com.ferosales.postulacionesapp.dto.response.CommentResponse;
import com.ferosales.postulacionesapp.entity.CompanyEntity;
import com.ferosales.postulacionesapp.entity.OpinionEntity;
import com.ferosales.postulacionesapp.repository.CompanyRepository;
import com.ferosales.postulacionesapp.repository.OpinionRepository;
import com.ferosales.postulacionesapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private OpinionRepository opinionRepository;

    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public void saveComment(Comment comment) {
    CompanyEntity company = companyRepository.findByName(comment.getCompany());
    List<OpinionEntity> opinions = opinionRepository.findByCompanyId((long) company.getId());
        if (!opinions.isEmpty()) {
            opinions.forEach(opinion -> {
                opinion.setPersonalOpinion(comment.getComment());
                opinionRepository.save(opinion);
            });
        }else {
            OpinionEntity opinion = new OpinionEntity();
            opinion.setPersonalOpinion(comment.getComment());
            opinion.setCompany(company);
            opinionRepository.save(opinion);
        }
    }

    @Override
    public List<CommentResponse> viewComments() {
        List<OpinionEntity> opinions = opinionRepository.findAllByOrderByIdDesc();

        return opinions.stream()
                .filter(opinion -> opinion.getPersonalOpinion() != null && opinion.getGlassdoor().getValue() > 0)
                .map(opinion -> {
                    CommentResponse comment = new CommentResponse();
                    comment.setCompany(opinion.getCompany().getName());
                    comment.setValue(opinion.getGlassdoor().getValue());
                    if (opinion.getGlassdoor().getOpinions().size() == 1 && opinion.getGlassdoor().getOpinions().get(0).isEmpty()) {
                        comment.setOpinions(Collections.singletonList("No hay opiniones"));
                    } else {
                        comment.setOpinions(opinion.getGlassdoor().getOpinions());
                    }
                    comment.setComment(opinion.getPersonalOpinion());
                    return comment;
                })
                .collect(Collectors.toList());
    }

}
