package com.hrms.core.utilities.check;

import com.hrms.core.utilities.results.Result;
import com.hrms.entities.concretes.Candidate;
import com.hrms.entities.concretes.dtos.CandidateDto;


public interface Check {

    public Result check(CandidateDto candidate);

}
