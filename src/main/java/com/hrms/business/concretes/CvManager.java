package com.hrms.business.concretes;

import com.hrms.business.abstracts.CvService;
import com.hrms.core.utilities.results.*;
import com.hrms.dataAccess.abstracts.CandidateDao;
import com.hrms.dataAccess.abstracts.CvDao;
import com.hrms.entities.concretes.Cv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CvManager implements CvService {

    private CvDao cvDao;
    private CandidateDao candidateDao;

    @Autowired
    public CvManager(CvDao cvDao, CandidateDao candidateDao) {
        this.cvDao = cvDao;
        this.candidateDao = candidateDao;
    }

    @Override
    public DataResult<Cv> add(Cv cv) {
        if (!this.cvDao.existsById(cv.getId())) {
            return new ErrorDataResult<>(cv, "Cv does not exist");
        } else {
            this.cvDao.save(cv);
            return new SuccessDataResult<>("Cv has been added.");
        }
    }

    @Override
    public DataResult<List<Cv>> getAll() {
        return new SuccessDataResult<List<Cv>>(this.cvDao.findAll());
    }

    @Override
    public DataResult<List<Cv>> getAllExperienceAtDesc(Long id) {
        return new SuccessDataResult<List<Cv>>(this.cvDao.findAllByIdOrderByExperienceDateDesc(id));
    }

    @Override
    public DataResult<List<Cv>> getAllCandidatesCv(int id) {

        return new SuccessDataResult<List<Cv>>(this.cvDao.findAllByCandidateId(id));
    }

    @Override
    public Result updateGithub(String githubAddress, long cvId) {
        if(!this.cvDao.existsById(cvId)) {
            return new ErrorResult("There is no such cv.");
        }else if(!githubAddress.startsWith("https://github.com")){
            if(!githubAddress.startsWith("github.com")){
                return new ErrorResult("Not valid github address");
            }
        }

        Cv cv=this.cvDao.getById(cvId);
        cv.setGithubAddress(githubAddress);
        this.cvDao.save(cv);
        return new SuccessResult("Saved");
    }

    @Override
    public Result deleteGithub(long cvId) {
        if(!this.cvDao.existsById(cvId)){
            return new ErrorResult("There is no such cv.");
        }
        Cv cv=this.cvDao.getById(cvId);
        cv.setGithubAddress(null);
        return new SuccessResult("Github address has been deleted");
    }

    @Override
    public Result updateLinkedin(String linkedinLink, long cvId) {
        if(!this.cvDao.existsById(cvId)){
            return new ErrorResult("There is no such cv.");
        }else if(!linkedinLink.startsWith("https://www.linkedin.com") &&
                !linkedinLink.startsWith("www.linkedin.com") &&
                !linkedinLink.startsWith("https://linkedin.com") &&
                !linkedinLink.startsWith("linkedin.com")){
            return new ErrorResult("Not valid address");
        }
        Cv cv=this.cvDao.getById(cvId);
        cv.setLinkedinAddress(linkedinLink);
        this.cvDao.save(cv);
        return new SuccessResult("Saved");
    }

    @Override
    public Result deleteLinkedin(long cvId) {
        if(!this.cvDao.existsById(cvId)){
            return new ErrorResult("Böyle bir cv yok");
        }
        Cv cv=this.cvDao.getById(cvId);
        cv.setLinkedinAddress(null);
        this.cvDao.save(cv);
        return new SuccessResult("Linkedin address has been deleted");
    }

    @Override
    public Result updateCoverLetter(String coverLetter, long cvId) {
        if(!this.cvDao.existsById(cvId)){
            return new ErrorResult("Böyle bir cv yok");
        }
        Cv cv=this.cvDao.getById(cvId);
        cv.setCoverLetter(coverLetter);
        this.cvDao.save(cv);
        return new SuccessResult("Cover letter has been updated");
    }


    @Override
    public Result deleteCoverLetter(long cvId) {
        if(!this.cvDao.existsById(cvId)){
            return new ErrorResult("Böyle bir cv yok");
        }
        Cv cv=this.cvDao.getById(cvId);
        cv.setCoverLetter(null);
        this.cvDao.save(cv);
        return new SuccessResult("Cover letter has been deleted");
    }
}
