package com.hrms.business.concretes;

import com.hrms.business.abstracts.CvService;
import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.ErrorDataResult;
import com.hrms.core.utilities.results.SuccessDataResult;
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
    public DataResult<List<Cv>> getAllUniversityNameAtDesc(String universityName) {
        return new SuccessDataResult<List<Cv>>(this.cvDao.findAllByUniversityNameOrderByGradDateDesc(universityName));
    }

    @Override
    public DataResult<List<Cv>> getAllCandidatesCv(int id) {

        return new SuccessDataResult<List<Cv>>(this.cvDao.findAllByCandidateId(id));
    }
}
