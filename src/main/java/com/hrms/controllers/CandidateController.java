package com.hrms.controllers;

import com.hrms.business.abstracts.CandidateService;
import com.hrms.core.utilities.results.DataResult;
import com.hrms.entities.concretes.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin  //default olarak dışarıya izin verir
public class CandidateController {

    private CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/add")
    public DataResult<Candidate> addCandidate(@RequestBody Candidate candidate) {
        return candidateService.add(candidate);
    }

    @GetMapping("/getAll")
    public DataResult<List<Candidate>> getAll() {
        return candidateService.getAll();
    }

    @GetMapping("/getByCandidateId")
    public DataResult<Candidate> getByCandidateId(@RequestParam int id) {
        return this.candidateService.getByCandidateId(id);
    }
}
