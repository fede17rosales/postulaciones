package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.dto.request.Postulation;
import com.ferosales.postulacionesapp.dto.response.PostulationResponse;
import com.ferosales.postulacionesapp.entity.*;
import com.ferosales.postulacionesapp.service.PostulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostulationServiceImpl implements PostulationService {
    private OfferServiceImpl offerService;
    private CompanyServiceImpl companyService;
    private GlassdoorServiceImpl glassdoorService;
    private PublicationServiceImpl publicationService;
    private OpinionServiceImpl opinionService;
    private ResponsibilityServiceImpl responsibilityService;
    private TaskServiceImpl taskService;

    public PostulationServiceImpl(OfferServiceImpl offerService, CompanyServiceImpl companyService, GlassdoorServiceImpl glassdoorService,
                                  PublicationServiceImpl publicationService, OpinionServiceImpl opinionService,
                                  ResponsibilityServiceImpl responsibilityService, TaskServiceImpl taskService) {
        this.offerService = offerService;
        this.companyService = companyService;
        this.glassdoorService = glassdoorService;
        this.publicationService = publicationService;
        this.opinionService = opinionService;
        this.responsibilityService = responsibilityService;
        this.taskService = taskService;
    }

    public List<PostulationResponse> viewPostulations() {
        List<PublicationEntity> publications = publicationService.viewPublications();
        return publications.stream()
                .map(this::convertPostulationResponse)
                .collect(Collectors.toList());
    }

    public void savePostulation(Postulation postulation) {
        CompanyEntity company = companyService.findCompanyByName(postulation.getCompany());
        if (company == null){
            company = companyService.saveCompany(createCompany(postulation));
        }
        OfferEntity offer = offerService.saveOffer(createOffer(postulation));
        for (String responsibility : postulation.getResponsibilities()) {
            ResponsibilityEntity resp = new ResponsibilityEntity();
            resp.setDescription(responsibility);
            responsibilityService.saveResponsibility(resp);
            taskService.saveTask(createTask(offer,resp));
        }
        GlassdoorEntity glassdoor = glassdoorService.saveGlassdoor(createGlassdoor(postulation));
        publicationService.savePublication(createPostulation(postulation,offer,company));
        opinionService.save(createOpinion(company,glassdoor));
    }

    public void deletePostulation(Long id) {
        Optional<PublicationEntity> publication = publicationService.getPublication(id);
        List<TaskEntity> tasks = taskService.findTaskByOfferId(publication.get().getOffer().getId());
        Optional<OfferEntity> offer = offerService.findOfferById(publication.get().getOffer().getId());
        publicationService.deletePublication(publication.get().getId());
        tasks.stream().map(TaskEntity::getId).forEach(taskService::deleteById);
        offerService.deleteOfferById(offer.get().getId());
    }

    private OfferEntity createOffer(Postulation postulation){
        OfferEntity offer = new OfferEntity();
        offer.setTitle(postulation.getTitle());
        offer.setDescription(postulation.getDescription());
        offer.setRecruiter(postulation.getRecruiter());
        offer.setSalary(postulation.getSalary());
        offer.setBenefits(postulation.getBenefits());
        offer.setEnglish(postulation.getEnglish());
        offer.setStatus("Activa");
        return offer;
    }

    private CompanyEntity createCompany(Postulation postulation){
        CompanyEntity company = new CompanyEntity();
        company.setName(postulation.getCompany());
        company.setAntique(postulation.getAntique());
        company.setType(postulation.getType());
        company.setEmployees(postulation.getEmployees());
        return company;
    }

    private GlassdoorEntity createGlassdoor(Postulation postulation){
        GlassdoorEntity glassdoor = new GlassdoorEntity();
        glassdoor.setOpinions(postulation.getOpinions());
        glassdoor.setSalary(postulation.getSalary());
        glassdoor.setValue(postulation.getValidation());
        return  glassdoor;
    }

    private PublicationEntity createPostulation(Postulation postulation, OfferEntity offer, CompanyEntity company){
        PublicationEntity publication = new PublicationEntity();
        publication.setDatePublication(postulation.getDatePublication());
        publication.setOffer(offer);
        publication.setCompany(company);
        return publication;
    }

    private OpinionEntity createOpinion(CompanyEntity company, GlassdoorEntity glassdoor){
        OpinionEntity opinion = new OpinionEntity();
        opinion.setCompany(company);
        opinion.setGlassdoor(glassdoor);
        return opinion;
    }

    private TaskEntity createTask(OfferEntity offer, ResponsibilityEntity responsibility){
        TaskEntity task = new TaskEntity();
        task.setOffer(offer);
        task.setResponsibility(responsibility);
        return task;
    }

    private PostulationResponse convertPostulationResponse(PublicationEntity publication){
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        List<TaskEntity> tasks = taskService.viewTasks();
        PostulationResponse postulationResponse = new PostulationResponse();
        postulationResponse.setId(publication.getId());
        postulationResponse.setCompany(publication.getCompany().getName());
        postulationResponse.setTitle(publication.getOffer().getTitle());
        postulationResponse.setDescription(publication.getOffer().getDescription());
        postulationResponse.setResponsibilities(tasks.stream()
                .filter(task -> task.getOffer().getId().equals(publication.getOffer().getId()))
                .map(task -> task.getResponsibility().getDescription())
                .collect(Collectors.toList()));
        postulationResponse.setRecruiter(publication.getOffer().getRecruiter());
        postulationResponse.setSalary(setSalaryCoin(publication.getOffer().getSalary()));
        postulationResponse.setDate(simpleDateFormat.format(publication.getDatePublication()));
        return postulationResponse;
    }

    private String setSalaryCoin(Double salary){
        if (salary == null){
            return "No especificado";
        } else if(salary <= 3000){
            return "$" + salary + " USD";
        }
        return "$" + salary + " ARS";
    }

}
