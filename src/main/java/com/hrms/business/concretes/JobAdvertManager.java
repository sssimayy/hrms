package com.hrms.business.concretes;

import com.hrms.business.abstracts.*;
import com.hrms.core.utilities.results.*;
import com.hrms.dataAccess.abstracts.JobAdActivationDao;
import com.hrms.dataAccess.abstracts.JobAdvertDao;
import com.hrms.dataAccess.abstracts.StaffDao;
import com.hrms.entities.concretes.JobAdActivation;
import com.hrms.entities.concretes.JobAdvert;
import com.hrms.entities.concretes.dtos.JobPostingSubmitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class JobAdvertManager implements JobAdvertService {

    private JobAdvertDao jobAdvertDao;
    private CityService cityService;
    private JobPositionService jobPositionService;
    private CandidateService candidateService;
    private WorkingPlaceService workingPlaceService;
    private WorkingTimeService workingTimeService;
    private EmployerService employerService;
    private StaffDao staffDao;
    private JobAdActivationDao jobAdActivationDao;

    @Autowired
    public JobAdvertManager(JobAdvertDao jobAdvertDao, CityService cityService, JobPositionService jobPositionService, CandidateService candidateService, WorkingPlaceService workingPlaceService, WorkingTimeService workingTimeService, EmployerService employerService, StaffDao staffDao, JobAdActivationDao jobAdActivationDao) {
        this.jobAdvertDao = jobAdvertDao;
        this.cityService = cityService;
        this.jobPositionService = jobPositionService;
        this.candidateService = candidateService;
        this.workingPlaceService = workingPlaceService;
        this.workingTimeService = workingTimeService;
        this.employerService = employerService;
        this.staffDao = staffDao;
        this.jobAdActivationDao = jobAdActivationDao;
    }

    @Override
    public Result add(JobPostingSubmitDto jobPostingSubmitDto) {
        if (jobPostingSubmitDto.getSalaryMin() >= jobPostingSubmitDto.getSalaryMax()) {
            return new ErrorResult("Minumum maaş maksimum maaşa eşit yada büyük olamaz");
        } else if (jobPostingSubmitDto.getOpenPositionCount() < 1) {
            return new ErrorResult("Open position count 1 den küçük olamaz.");
        }
        JobAdvert jobAdvert = new JobAdvert();

        jobAdvert.setDescription(jobPostingSubmitDto.getDescription());
        jobAdvert.setSalaryMax(jobPostingSubmitDto.getSalaryMax());
        jobAdvert.setSalaryMin(jobPostingSubmitDto.getSalaryMin());
        jobAdvert.setDeadline(jobPostingSubmitDto.getDeadline());
        jobAdvert.setActive(true);
        jobAdvert.setOpenPositionCount(jobPostingSubmitDto.getOpenPositionCount());
        jobAdvert.setPublishedAt(jobPostingSubmitDto.getPublishedAt());
        jobAdvert.setCreatedAt(jobPostingSubmitDto.getCreatedAt());
        jobAdvert.setDeleted(false);
        jobAdvert.setEmployer(employerService.findByEmail(jobPostingSubmitDto.getEmail()).getData());
        jobAdvert.setCity(cityService.findById(jobPostingSubmitDto.getCityId()).getData());
        jobAdvert.setJobPosition(jobPositionService.getById(jobPostingSubmitDto.getJobPositionId()).getData());
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
            return new ErrorDataResult<List<JobAdvert>>("There is no such an employer.");
        } else {
            return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getEmployersActiveAdvert(id), "Başarılı");
        }
    }

    @Override
    public Result setPasssive(int jobAdId) {
        try {
            JobAdvert jobAd=this.jobAdvertDao.getById(jobAdId);
            jobAd.setActive(false);
            jobAdvertDao.save(jobAd);
            return new SuccessResult("Advert has been disactivated");
        }catch (EntityNotFoundException exception){
            return new ErrorResult("There is no such an advert");
        }
    }

    @Override
    public Result setActiveAndConfirm(int jobAdId, int staffId) {
        try{
            if(!this.staffDao.existsById(staffId)){
                return new ErrorResult("There is no such a staff.");
            }
            JobAdActivation jobAdActivation=this.jobAdActivationDao.getById(jobAdId);
            jobAdActivation.setConfirmDate(LocalDate.now());
            jobAdActivation.setConfirm(true);
            jobAdActivation.setStaffId(staffId);
            this.jobAdActivationDao.save(jobAdActivation);

            JobAdvert jobAd=this.jobAdvertDao.getById(jobAdId);
            jobAd.setActive(true);
            jobAd.setConfirmed(true);
            this.jobAdvertDao.save(jobAd);
            return new SuccessResult("Advert has been activated");
        }catch (EntityNotFoundException exception){
            return new ErrorResult("There is no such an advert");
        }

    }



}

