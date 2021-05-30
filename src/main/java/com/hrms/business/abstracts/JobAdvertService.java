package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.entities.concretes.JobAdvert;

import java.util.List;

public interface JobAdvertService {
    Result add(JobAdvert jobAdvert);
    Result update(JobAdvert jobAdvert);
    DataResult<JobAdvert> getById(int id);
    Result changeOpenToClose(int id);
    DataResult<List<JobAdvert>> getAll();
    DataResult<List<JobAdvert>> getAllOpenJobAdvertList();
    DataResult<List<JobAdvert>> findAllByOrderByPublishedAt();
    DataResult<List<JobAdvert>> getAllOpenJobAdvertByEmployer(int id);
}
