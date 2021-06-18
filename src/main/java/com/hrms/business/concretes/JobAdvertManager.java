package com.hrms.business.concretes;

import com.hrms.business.abstracts.*;
import com.hrms.core.utilities.results.*;
import com.hrms.dataAccess.abstracts.JobAdvertDao;
import com.hrms.entities.concretes.JobAdvert;
import com.hrms.entities.concretes.dtos.JobPostingSubmitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAdvertManager implements JobAdvertService {

    private JobAdvertDao jobAdvertDao;
    private CityService cityService;
    private JobPositionService jobPositionService;
    private CandidateService candidateService;
    private WorkingPlaceService workingPlaceService;
    private WorkingTimeService workingTimeService;

    @Autowired
    public JobAdvertManager(JobAdvertDao jobAdvertDao, CityService cityService, JobPositionService jobPositionService, CandidateService candidateService, WorkingPlaceService workingPlaceService, WorkingTimeService workingTimeService) {
        this.jobAdvertDao = jobAdvertDao;
        this.cityService = cityService;
        this.jobPositionService = jobPositionService;
        this.candidateService = candidateService;
        this.workingPlaceService = workingPlaceService;
        this.workingTimeService = workingTimeService;
    }


    @Override
    public Result add(JobPostingSubmitDto jobPostingSubmitDto) {
        JobAdvert jobAdvert = new JobAdvert();

        jobAdvert.setDescription(jobPostingSubmitDto.getDescription());
        jobAdvert.setSalaryMax(jobPostingSubmitDto.getSalaryMax());
        jobAdvert.setSalaryMin(jobPostingSubmitDto.getSalaryMin());
        jobAdvert.setDeadline(jobPostingSubmitDto.getDeadline());
        jobAdvert.setActive(true);
        jobAdvert.setCompanyName(jobPostingSubmitDto.getCompanyName());
        jobAdvert.setPhone(jobPostingSubmitDto.getPhone());
        jobAdvert.setWebsite(jobPostingSubmitDto.getWebsite());
        jobAdvert.setEmail(jobPostingSubmitDto.getEmail());
        jobAdvert.setOpenPositionCount(jobPostingSubmitDto.getOpenPositionCount());
        jobAdvert.setPublishedAt(jobPostingSubmitDto.getPublishedAt());
        jobAdvert.setCreatedAt(jobPostingSubmitDto.getCreatedAt());
        jobAdvert.setDeleted(false);
        jobAdvert.setCity(cityService.findById(jobPostingSubmitDto.getCityId()).getData());
        jobAdvert.setJobPosition(jobPositionService.getById(jobPostingSubmitDto.getJobPositionId()).getData());
        jobAdvert.setCandidate(candidateService.getByCandidateId(jobPostingSubmitDto.getCandidateId()).getData());
        jobAdvert.setWorkingPlace(workingPlaceService.getById(jobPostingSubmitDto.getWorkingPlaceId()).getData());
        jobAdvert.setWorkingTime(workingTimeService.findById(jobPostingSubmitDto.getWorkingTimeId()).getData());

        jobAdvertDao.save(jobAdvert);
        return new SuccessResult("Success!");
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
        jobAdvert.setActive(false);

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

