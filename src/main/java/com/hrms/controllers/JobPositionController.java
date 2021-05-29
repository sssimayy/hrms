package com.hrms.controllers;

import com.hrms.business.abstracts.JobPositionService;
import com.hrms.core.utilities.results.DataResult;
import com.hrms.entities.concretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobPosition")
public class JobPositionController {

    private JobPositionService jobPositionService;

    @Autowired
    public JobPositionController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    @PostMapping("/add")
    public DataResult<JobPosition> addJobPosition(@RequestBody JobPosition jobPosition) {
        return jobPositionService.addJobPosition(jobPosition);
    }

    @GetMapping("/getAll")
    public DataResult<List<JobPosition>> getAllJobPositions() {
        return jobPositionService.getAllJobPositios();
    }

}
