package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.entities.concretes.Cv;

import java.util.List;

public interface CvService {
    DataResult<Cv> add(Cv cv);

    DataResult<List<Cv>> getAll();

    DataResult<List<Cv>> getAllExperienceAtDesc(Long id);

    DataResult<List<Cv>> getAllUniversityNameAtDesc(String universityName);

    DataResult<List<Cv>> getAllCandidatesCv(String name);
}
