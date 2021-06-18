package com.hrms.business.concretes;

import com.hrms.business.abstracts.JobPositionService;
import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.ErrorDataResult;
import com.hrms.core.utilities.results.SuccessDataResult;
import com.hrms.dataAccess.abstracts.JobPositionDao;
import com.hrms.entities.concretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPositionManager implements JobPositionService {

    private JobPositionDao jobPositionDao;

    @Autowired
    public JobPositionManager(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    @Override
    public DataResult<JobPosition> addJobPosition(JobPosition jobPosition) {
        if (getJobByTitle(jobPosition.getTitle()).getData() != null) {
            return new ErrorDataResult<>("This job already exist.");
        }
        return new SuccessDataResult<>(
                jobPositionDao.saveAndFlush(jobPosition),
                "Data saved successfully."
        );
    }

    @Override
    public DataResult<List<JobPosition>> getAllJobPositions() {
        return new SuccessDataResult<>(this.jobPositionDao.findAll(), "Data listed successfully");
    }

    @Override
    public DataResult<JobPosition> getJobByTitle(String title) {
        return new SuccessDataResult<JobPosition>(this.jobPositionDao.existsByTitle(title));
    }

    @Override
    public DataResult<JobPosition> getById(int id) {
        JobPosition jobPositionInDB = jobPositionDao.getOne(id);

        if (jobPositionInDB == null) {
            return new ErrorDataResult<>("Data not found!");
        }

        return new SuccessDataResult<>(jobPositionInDB, "Data found");
    }

}
