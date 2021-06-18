package com.hrms.controllers;

import com.hrms.business.abstracts.CandidateService;
import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.entities.concretes.Candidate;
import com.hrms.entities.concretes.dtos.CandidateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Result addCandidate(@Valid @RequestBody CandidateDto candidateDto) {
        return candidateService.add(candidateDto);
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
