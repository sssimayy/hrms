package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.entities.concretes.Language;

import java.util.List;

public interface LanguageService {
    DataResult<List<Language>> getAllLanguages();
}
