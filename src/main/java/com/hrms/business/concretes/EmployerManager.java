package com.hrms.business.concretes;

import com.hrms.business.abstracts.EmployerService;
import com.hrms.core.utilities.results.*;
import com.hrms.dataAccess.abstracts.EmployerDao;
import com.hrms.entities.concretes.Employer;
import com.hrms.entities.concretes.dtos.EmployerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerManager implements EmployerService {

    private EmployerDao employerDao;

    @Autowired
    public EmployerManager(EmployerDao employerDao) {
        this.employerDao = employerDao;
    }

    @Override
    public Result add(EmployerDto employer) {
        if (!employer.getPassword().equals(employer.getPasswordRepeat())) {
            return new ErrorResult("Passwords do not match!");
        }

        Employer emailInDb = employerDao.findByEmail(employer.getEmail());

        if (emailInDb != null) {
            return new ErrorResult("This mail address already exist!");
        }

        String email = employer.getEmail();
        String website = employer.getWebsite();

        if (!email.substring(email.indexOf("@") + 1).equals(website.substring(website.indexOf(".") + 1))) {
            return new ErrorResult("Email address should be same with domain name!");
        }

        Employer employerRegister = new Employer();
        employerRegister.setCompanyName(employer.getCompanyName());
        employerRegister.setWebsite(employer.getWebsite());
        employerRegister.setEmail(employer.getEmail());
        employerRegister.setPhone(employer.getPhone());
        employerRegister.setPassword(employer.getPassword());


        employerDao.save(employerRegister);

        return new SuccessResult("Successful");
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<>(employerDao.findAll(), "Data is listed");

    }

    @Override
    public DataResult<Employer> findByEmail(String email) {
        Employer employerInDB = employerDao.findByEmail(email);

        if (employerInDB == null) {
            return new ErrorDataResult<>("Data not found!");
        }

        return new SuccessDataResult<>(employerInDB, "Data is faund");
    }

}
