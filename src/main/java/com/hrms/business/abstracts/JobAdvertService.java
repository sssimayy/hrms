package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.entities.concretes.JobAdvert;
import com.hrms.entities.concretes.dtos.JobPostingSubmitDto;

import java.util.List;

public interface JobAdvertService {
    Result add(JobPostingSubmitDto jobPostingSubmitDto);

    Result update(JobAdvert jobAdvert);

    DataResult<List<JobAdvert>> getAll();

    DataResult<JobAdvert> getById(int id);

    Result changeOpenToClose(int id);

    DataResult<List<JobAdvert>> getAllActiveAdvert();

    DataResult<List<JobAdvert>> findAllByOrderByPublishedAtDesc();

    DataResult<List<JobAdvert>> getAllActiveAdvertsById(int id);

    DataResult<List<JobAdvert>> findAllByIsActiveAndCompanyName(int id);
}
