package com.hrms.core.utilities.check;

import com.hrms.core.utilities.results.ErrorResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.core.utilities.results.SuccessResult;
import com.hrms.entities.concretes.Candidate;
import org.springframework.stereotype.Component;

@Component
public class ConfirmPasswordCheck implements Check {


    public Result check(Candidate candidate) {
        if (candidate.getPassword().equals(candidate.getPasswordCheck())) {
            return new SuccessResult();
        }
        return new ErrorResult("Password and Confirm Password fields are not equals.");
    }
}
