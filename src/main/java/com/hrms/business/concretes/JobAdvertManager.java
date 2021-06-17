package com.hrms.business.concretes;

import com.hrms.business.abstracts.JobAdvertService;
import com.hrms.core.utilities.results.*;
import com.hrms.dataAccess.abstracts.JobAdvertDao;
import com.hrms.entities.concretes.JobAdvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAdvertManager implements JobAdvertService {

    private JobAdvertDao jobAdvertDao;

    @Autowired
    public JobAdvertManager(JobAdvertDao jobAdvertDao) {
        this.jobAdvertDao = jobAdvertDao;
    }

    @Override
    public Result add(JobAdvert jobAdvert) {
        if (!checkNullArea(jobAdvert)) {
            return new ErrorResult("You have entered missing information. Please fill in all fields.");
        }
        if (!salaryCheck(jobAdvert)) {
            return new ErrorResult("Min salary can not much than max salary.");

        }
        this.jobAdvertDao.save(jobAdvert);
        return new SuccessResult("Job advert has been added.");
    }

    @Override
    public Result update(JobAdvert jobAdvert) {
        return null;
    }

    @Override
    public DataResult<List<JobAdvert>> getAll() {
        return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAll());
    }

    @Override
    public DataResult<JobAdvert> getById(int id) {
        return null;
    }

    @Override
    public DataResult<JobAdvert> changeOpenToClose(int id) {
        if (!this.jobAdvertDao.existsById(id)) {
            return new ErrorDataResult<JobAdvert>("There is no such job advert");

        }

        JobAdvert jobAdvert = this.jobAdvertDao.getOne(id);
        jobAdvert.setOpen(false);

        return new SuccessDataResult<JobAdvert>(this.jobAdvertDao.save(jobAdvert), "Job advert has been successfully closed.");
    }

    @Override
    public DataResult<List<JobAdvert>> getAllActiveAdvert() {
        return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAllByIsActive(true), "All open adverts are listed");
    }

    @Override
    public DataResult<List<JobAdvert>> findAllByOrderByPublishedAtDesc() {
        return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAllByIsActiveOrderByPublishedAtDesc(true), "All adverts are listed according to published at");
    }

    @Override
    public DataResult<List<JobAdvert>> getAllActiveAdvertsById(int id) {
        return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAllByJobId(id), "All open adverts are listed");
    }

    @Override
    public DataResult<List<JobAdvert>> findAllByIsActiveAndCompanyName(int id) {
        if (!this.jobAdvertDao.existsById(id)) {
            return new ErrorDataResult<List<JobAdvert>>("İş veren bulunamadı");
        } else {
            return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getEmployersActiveAdvert(id), "Başarılı");
        }
    }

    private boolean checkNullArea(JobAdvert jobAdvert) {
        if (jobAdvert.getJobPosition() != null && jobAdvert.getDescription() != null && jobAdvert.getCity() != null && jobAdvert.getOpenPositionCount() != 0) {
            return true;
        }
        return false;
    }

    private boolean salaryCheck(JobAdvert jobAdvert) {
        if (jobAdvert.getSalaryMin() > jobAdvert.getSalaryMax()) {
            return false;
        } else
            return true;
    }
}

