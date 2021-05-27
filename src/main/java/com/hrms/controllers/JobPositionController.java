package com.hrms.controllers;

import com.hrms.business.abstracts.JobPositionService;
import com.hrms.entities.conretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobPosition")
public class JobPositionController {

    private JobPositionService jobPositionService;

    @Autowired
    public JobPositionController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    @GetMapping("/getAll")
    public List<JobPosition> getAllJobPositions(){
        return jobPositionService.getAllJobPositios();
    }

}
