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
        this.cvDao.save(cv);
        return new SuccessDataResult<>("Cv has been added.");
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
    public DataResult<List<Cv>> getAllUniversityNameAtDesc(String universityName) {
        return new SuccessDataResult<List<Cv>>(this.cvDao.findAllByUniversityNameOrderByGradDateDesc(universityName));
    }


    @Override
    public DataResult<List<Cv>> getAllCandidatesCv(String name) {
//        if (!this.cvDao.existsByName(name)) {
//            return new ErrorDataResult<List<Cv>>("Cv does not exist");
//        } else {
            return new SuccessDataResult<List<Cv>>(this.candidateDao.findAllByName(name));
        }

}
