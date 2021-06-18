package com.hrms.core.utilities.check;

import com.hrms.core.utilities.results.ErrorResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.core.utilities.results.SuccessResult;
import com.hrms.entities.concretes.Candidate;
import com.hrms.entities.concretes.dtos.CandidateDto;
import org.springframework.stereotype.Component;

@Component
public class ConfirmPasswordCheck implements Check {


    public Result check(CandidateDto candidate) {
        if (candidate.getPassword().equals(candidate.getPasswordCheck())) {
            return new SuccessResult();
        }
        return new ErrorResult("Password and Confirm Password fields are not equals.");
    }
}
