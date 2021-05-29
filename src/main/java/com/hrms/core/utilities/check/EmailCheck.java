package com.hrms.core.utilities.check;

import com.hrms.core.utilities.results.ErrorResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.core.utilities.results.SuccessResult;
import com.hrms.dataAccess.abstracts.UserDao;
import com.hrms.entities.concretes.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailCheck implements Check {

    private UserDao userDao;

    @Autowired
    public EmailCheck(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Result check(Candidate candidate) {
        if (userDao.existsByEmail(candidate.getEmail())) {
            return new ErrorResult("This email is already exist.");
        }
        return new SuccessResult();
    }
}
