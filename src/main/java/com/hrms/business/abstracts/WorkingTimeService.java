package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.entities.concretes.WorkingTime;

import java.util.List;

public interface WorkingTimeService {
    DataResult<List<WorkingTime>> getAllWorkingTime();
}
