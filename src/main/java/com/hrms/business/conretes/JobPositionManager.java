package com.hrms.business.conretes;

import com.hrms.business.abstracts.JobPositionService;
import com.hrms.dataAccess.abstracts.JobPositionDao;
import com.hrms.entities.conretes.JobPosition;
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
    public List<JobPosition> getAllJobPositios() {
        return this.jobPositionDao.findAll();
    }
}
