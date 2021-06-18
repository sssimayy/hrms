package com.hrms.core.adapter.abstracts.mernis;

import com.hrms.core.utilities.results.Result;
import com.hrms.entities.concretes.Candidate;
import com.hrms.entities.concretes.dtos.CandidateDto;

public interface MernisService {

    public Result checkForPersonelInformation(CandidateDto candidate);

}
