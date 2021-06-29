package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.entities.concretes.Candidate;
import com.hrms.entities.concretes.dtos.CandidateDto;

import java.util.List;

public interface CandidateService {

    Result add(CandidateDto candidateDto);

    DataResult<List<Candidate>> getAll();

    DataResult<Candidate> getByCandidateId(int id);

    DataResult<Candidate> getByEmail(String email);
}
