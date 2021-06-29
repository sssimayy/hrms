package com.hrms.controllers;

import com.hrms.business.abstracts.CvService;
import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.entities.concretes.Cv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/getAllCandidatesCv")
    public DataResult<List<Cv>> getAllCandidatesCv(@RequestParam int id) {
        return this.cvService.getAllCandidatesCv(id);
    }

    @PutMapping("/updateGithub")
    public ResponseEntity<?> updateGithub(@RequestParam String githublink, @RequestParam int cvId){
        Result result=this.cvService.updateGithub(githublink,cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/deleteGithub")
    public ResponseEntity<?> deleteGithub(@RequestParam int cvId){
        Result result=this.cvService.deleteGithub(cvId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/updateLinkedin")
    public ResponseEntity<?> updateLinkedin(@RequestParam String linkedinAddress,@RequestParam int cvId){
        Result result=this.cvService.updateLinkedin(linkedinAddress,cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/deleteLinkedin")
    public ResponseEntity<?> deleteLinkedin(@RequestParam int cvId){
        Result result=this.cvService.deleteLinkedin(cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/updateCoverLetter")
    public ResponseEntity<?> updateCoverLetter(@RequestParam String coverLetter,@RequestParam int cvId){
        Result result=this.cvService.updateCoverLetter(coverLetter,cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/deleteCoverLetter")
    public ResponseEntity<?> deleteCoverLetter(@RequestParam int cvId){
        Result result=this.cvService.deleteCoverLetter(cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}
