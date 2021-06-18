package com.hrms.core.utilities.check;

import com.hrms.core.utilities.results.ErrorResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.core.utilities.results.SuccessResult;
import com.hrms.dataAccess.abstracts.CandidateDao;
import com.hrms.entities.concretes.dtos.CandidateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NationalIdentityCheck implements Check {

    private CandidateDao candidateDao;

    @Autowired
    public NationalIdentityCheck(CandidateDao candidateDao) {
        this.candidateDao = candidateDao;
    }

    @Override
    public Result check(CandidateDto candidate) {
        if (candidateDao.existsByNationalIdentity(candidate.getNationalIdentity())) {
            return new ErrorResult("This national identity is already exists.");
        }
        return new SuccessResult();
    }
}
