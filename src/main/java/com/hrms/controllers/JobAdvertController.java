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
    public Result add(@RequestBody JobAdvert jobAdvert){
        return this.jobAdvertService.add(jobAdvert);
    }
    @GetMapping("/getAll")
    public DataResult<List<JobAdvert>> getAll(){
        return this.jobAdvertService.getAll();
    }

    @PostMapping("/changeOpenToClose")
    public Result changeOpenToClose(int id){
        return this.jobAdvertService.changeOpenToClose(id);
    }

    @GetMapping("/getAllOpenJobAdvertList")
    public DataResult<List<JobAdvert>> getAllOpenJobAdvertList(){
        return this.jobAdvertService.getAllOpenJobAdvertList();
    }

    @GetMapping("/findAllByOrderByPublishedAt")
    public DataResult<List<JobAdvert>> findAllByOrderByPublishedAt(){
        return this.jobAdvertService.findAllByOrderByPublishedAt();
    }

    @GetMapping("/getAllOpenJobAdvertByEmployer")
    public DataResult<List<JobAdvert>> getAllOpenJobAdvertByEmployer(int id){
        return this.jobAdvertService.getAllOpenJobAdvertByEmployer(id);
    }
}
