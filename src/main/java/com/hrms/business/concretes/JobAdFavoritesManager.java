package com.hrms.business.concretes;

import com.hrms.business.abstracts.JobAdFavoritesService;
import com.hrms.core.utilities.results.*;
import com.hrms.dataAccess.abstracts.CandidateDao;
import com.hrms.dataAccess.abstracts.JobAdFavoritesDao;
import com.hrms.dataAccess.abstracts.JobAdvertDao;
import com.hrms.entities.concretes.JobAdFavorites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAdFavoritesManager implements JobAdFavoritesService {

    private JobAdFavoritesDao jobAdFavoritesDao;
    private CandidateDao candidateDao;
    private JobAdvertDao jobAdDao;

    @Autowired
    public JobAdFavoritesManager(JobAdFavoritesDao jobAdFavoritesDao, CandidateDao candidateDao, JobAdvertDao jobAdDao) {
        this.jobAdFavoritesDao = jobAdFavoritesDao;
        this.candidateDao=candidateDao;
        this.jobAdDao=jobAdDao;
    }

    @Override
    public DataResult<List<JobAdFavorites>> getByCandidateId(long candidateId) {
        if(!this.candidateDao.existsById(candidateId)){
            return new ErrorDataResult<List<JobAdFavorites>>("There is no such a user.");
        }
        return new SuccessDataResult<List<JobAdFavorites>>(this.jobAdFavoritesDao.findByCandidateId(candidateId),"Data has been listed");
    }

    @Override
    public Result addFavorite(long candidateId, long jobAdId) {
        if(!this.candidateDao.existsById(candidateId)){
            return new ErrorResult("There is no such a user.");
        }else if(!this.jobAdDao.existsById(jobAdId)){
            return new ErrorResult("There is no such an advert.");
        }

        JobAdFavorites jobAdFavorites=new JobAdFavorites();
        jobAdFavorites.setCandidate(this.candidateDao.getById(candidateId));
        jobAdFavorites.setJobAd(this.jobAdDao.getById(jobAdId));
        this.jobAdFavoritesDao.save(jobAdFavorites);
        return new SuccessResult("Has been added to favorites.");
    }
}
