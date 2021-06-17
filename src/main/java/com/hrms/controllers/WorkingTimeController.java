package com.hrms.controllers;

import com.hrms.business.abstracts.WorkingTimeService;
import com.hrms.core.utilities.results.DataResult;
import com.hrms.entities.concretes.WorkingTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/workingtime")
@CrossOrigin
public class WorkingTimeController {
    private WorkingTimeService workingTimeService;

    @Autowired

    public WorkingTimeController(WorkingTimeService workingTimeService) {
        this.workingTimeService = workingTimeService;
    }

    @GetMapping("/getAll/workingTime")
    public DataResult<List<WorkingTime>> getAllWorkingTime(){
        return this.workingTimeService.getAllWorkingTime();
    }
}
