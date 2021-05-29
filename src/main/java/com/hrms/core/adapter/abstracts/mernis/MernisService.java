package com.hrms.core.adapter.abstracts.mernis;

import com.hrms.core.utilities.results.Result;
import com.hrms.entities.concretes.Candidate;

public interface MernisService {

    public Result checkForPersonelInformation(Candidate candidate);

}
