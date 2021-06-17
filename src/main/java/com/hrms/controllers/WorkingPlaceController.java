package com.hrms.controllers;

import com.hrms.business.abstracts.WorkingPlaceService;
import com.hrms.core.utilities.results.DataResult;
import com.hrms.entities.concretes.WorkingPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/workingplace")
@CrossOrigin
public class WorkingPlaceController {

    private WorkingPlaceService workingPlaceService;

    @Autowired

    public WorkingPlaceController(WorkingPlaceService workingPlaceService) {
        this.workingPlaceService = workingPlaceService;
    }

    @GetMapping("/getAll/workingPlace")
    public DataResult<List<WorkingPlace>> getAllWorkingPlace() {
        return this.workingPlaceService.getAllWorkingPlace();
    }
}
