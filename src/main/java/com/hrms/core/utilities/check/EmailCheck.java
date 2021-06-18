package com.hrms.core.utilities.check;

import com.hrms.core.utilities.results.ErrorResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.core.utilities.results.SuccessResult;
import com.hrms.dataAccess.abstracts.CandidateDao;
import com.hrms.entities.concretes.dtos.CandidateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailCheck implements Check {

    private CandidateDao userDao;

    @Autowired
    public EmailCheck(CandidateDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Result check(CandidateDto candidate) {
        if (userDao.existsByEmail(candidate.getEmail())) {
            return new ErrorResult("This email is already exist.");
        }
        return new SuccessResult();
    }


}
