package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.entities.concretes.JobAdFavorites;

import java.util.List;

public interface JobAdFavoritesService {
    public DataResult<List<JobAdFavorites>> getByCandidateId(long candidateId);
    public Result addFavorite(long candidateId, long jobAdId);
}
