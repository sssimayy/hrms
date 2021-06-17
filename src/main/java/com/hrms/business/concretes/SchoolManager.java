package com.hrms.business.concretes;

import com.hrms.business.abstracts.SchoolService;
import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.SuccessDataResult;
import com.hrms.dataAccess.abstracts.SchoolDao;
import com.hrms.entities.concretes.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolManager implements SchoolService {
    private SchoolDao schoolDao;

    @Autowired
    public SchoolManager(SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    @Override
    public DataResult<List<School>> getAllSchool() {
        return new SuccessDataResult<>(schoolDao.findAll(), "Data listed successfully");
    }
}
