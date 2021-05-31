package com.hrms.controllers;

import com.hrms.business.abstracts.JobAdvertService;
import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.entities.concretes.JobAdvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobAdvert")
public class JobAdvertController {
    private JobAdvertService jobAdvertService;

    @Autowired
    public JobAdvertController(JobAdvertService jobAdvertService) {
        this.jobAdvertService = jobAdvertService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobAdvert jobAdvert) {
        return this.jobAdvertService.add(jobAdvert);
    }

    @GetMapping("/gellAllAdverts")
    public DataResult<List<JobAdvert>> getAll() {
        return this.jobAdvertService.getAll();
    }

    @GetMapping("/getAllActiveAdverts")
    public DataResult<List<JobAdvert>> getAllActive() {
        return this.jobAdvertService.getAllActiveAdvert();
    }

    @PostMapping("/changeOpenToClose")
    public Result changeOpenToClose(int id) {
        return this.jobAdvertService.changeOpenToClose(id);
    }

    @GetMapping("/getAllByOrderByPublishedAtDesc")
    public DataResult<List<JobAdvert>> findAllByOrderByPublishedAtDesc() {
        return this.jobAdvertService.findAllByOrderByPublishedAtDesc();
    }

    @GetMapping("/getAllActiveAdvertsOfFirm")
    public DataResult<List<JobAdvert>> getAllActiveAdvertsOfFirm(@RequestParam String companyName) {
        return this.jobAdvertService.getAllActiveAdvertsOfFirm(companyName);
    }

    @GetMapping("/getEmployerJobAdvertisement")
    public DataResult<List<JobAdvert>> findAllByIsActiveAndCompanyName(int id) {
        return this.jobAdvertService.findAllByIsActiveAndCompanyName(id);
    }
}
