package com.hrms.controllers;

import com.hrms.business.abstracts.LanguageService;
import com.hrms.core.utilities.results.DataResult;
import com.hrms.entities.concretes.JobPosition;
import com.hrms.entities.concretes.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/language")
@CrossOrigin
public class LanguageController {

    private LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/getAll/language")
    public DataResult<List<Language>> getAllLanguages() {
        return languageService.getAllLanguages();
    }

}
