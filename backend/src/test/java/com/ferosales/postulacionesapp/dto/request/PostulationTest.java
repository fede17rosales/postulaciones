package com.ferosales.postulacionesapp.dto.request;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostulationTest {

        @Test
        void testToString() {
            Postulation postulation = new Postulation();
            postulation.setTitle("title");
            postulation.setCompany("company");
            postulation.setDescription("description");
            postulation.setResponsibilities(List.of("responsibilities"));
            postulation.setSalary(1000.0);
            postulation.setBenefits("benefits");
            postulation.setEnglish(true);
            postulation.setDatePublication(new Date("Thu Jan 01 00:00:00 UTC 2025"));
            postulation.setRecruiter("recruiter");
            postulation.setAntique(1);
            postulation.setType("type");
            postulation.setEmployees(1);
            postulation.setOpinions(List.of("opinions"));
            postulation.setValidation(1.0);
            postulation.setSalaryOpinion(1.0);

            String expected = "Postulation(title=title, company=company, description=description, responsibilities=[responsibilities], salary=1000.0, benefits=benefits, english=true, datePublication=Tue Dec 31 21:00:00 ART 2024, recruiter=recruiter, antique=1, type=type, employees=1, opinions=[opinions], validation=1.0, salaryOpinion=1.0)";
            assertEquals(expected, postulation.toString());
        }

}