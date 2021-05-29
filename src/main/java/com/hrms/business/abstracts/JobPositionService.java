package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.entities.concretes.JobPosition;

import java.util.List;

public interface JobPositionService {

    DataResult<JobPosition> addJobPosition(JobPosition jobPosition);

    DataResult<List<JobPosition>> getAllJobPositios();

}
