package com.hrms.controllers;

import com.hrms.business.abstracts.SchoolService;
import com.hrms.core.utilities.results.DataResult;
import com.hrms.entities.concretes.Cv;
import com.hrms.entities.concretes.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/school")
@CrossOrigin
public class SchoolController {
    private SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/getAll/school")
    public DataResult<List<School>> getAllSchool() {
        return this.schoolService.getAllSchool();
    }

    @GetMapping("/getAllUniversityNameDesc")
    public DataResult<List<Cv>> getAllUniversityNameDesc(@RequestParam String universityName) {
        return this.schoolService.getAllUniversityNameAtDesc(universityName);
    }
}
