package com.hrms.core.utilities.check;

import com.hrms.core.utilities.results.Result;
import com.hrms.entities.concretes.Candidate;


public interface Check {

    public Result check(Candidate candidate);

}
