package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.entities.concretes.Candidate;

import java.util.List;

public interface CandidateService {

    DataResult<Candidate> add(Candidate candidate);

    DataResult<List<Candidate>> getAll();

}
