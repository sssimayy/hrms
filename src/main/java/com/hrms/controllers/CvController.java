package com.hrms.controllers;

import com.hrms.business.abstracts.CvService;
import com.hrms.core.utilities.results.DataResult;
import com.hrms.entities.concretes.Cv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cv")
@CrossOrigin
public class CvController {

    private CvService cvService;

    @Autowired
    public CvController(CvService cvService) {
        this.cvService = cvService;
    }

    @PostMapping("/addCv")
    public DataResult<Cv> addCv(@RequestBody Cv cv) {
        return cvService.add(cv);
    }

    @GetMapping("/getAllCv")
    public DataResult<List<Cv>> getAll() {
        return cvService.getAll();
    }

    @GetMapping("/getAllExperienceDesc")
    public DataResult<List<Cv>> getAllExperienceDesc(@RequestParam Long id) {
        return this.cvService.getAllExperienceAtDesc(id);
    }

    @GetMapping("/getAllUniversityNameDesc")
    public DataResult<List<Cv>> getAllUniversityNameDesc(@RequestParam String universityName) {
        return this.cvService.getAllUniversityNameAtDesc(universityName);
    }

    @GetMapping("getAllCandidatesCv")
    public DataResult<List<Cv>> getAllCandidatesCv(@RequestParam int id) {
        return this.cvService.getAllCandidatesCv(id);
    }
}
