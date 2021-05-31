package com.hrms.business.concretes;

import com.hrms.business.abstracts.CvService;
import com.hrms.core.utilities.results.*;
import com.hrms.dataAccess.abstracts.CvDao;
import com.hrms.entities.concretes.Cv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CvManager implements CvService {

    private CvDao cvDao;

    @Autowired
    public CvManager(CvDao cvDao) {
        this.cvDao = cvDao;
    }

    @Override
    public Result add(Cv cv) {
        this.cvDao.save(cv);
        return new SuccessResult("Cv has been added.");

    }

    @Override
    public DataResult<List<Cv>> getAll() {
        return new SuccessDataResult<List<Cv>>(this.cvDao.findAll());
    }

    @Override
    public DataResult<List<Cv>> getAllExperienceAtDesc(String position) {
        return new SuccessDataResult<List<Cv>>(this.cvDao.findAllByPositionOrderByExperienceDateDesc(position));
    }

    @Override
    public DataResult<List<Cv>> getAllUniversityNameAtDesc(String universityName) {
        return new SuccessDataResult<List<Cv>>(this.cvDao.findAllByUniversityNameOrderByGradDateDesc(universityName));
    }

    @Override
    public DataResult<List<Cv>> getAllCandidatesCv(int id) {
        if (!this.cvDao.existsById(id)) {
            return new ErrorDataResult<List<Cv>>("Cv does not exist");
        } else {
            return new SuccessDataResult<List<Cv>>(this.cvDao.getCvInfoOfCandidate(id));
        }
    }
}
