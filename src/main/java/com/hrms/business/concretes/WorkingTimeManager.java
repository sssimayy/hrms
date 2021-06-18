package com.hrms.business.concretes;

import com.hrms.business.abstracts.WorkingTimeService;
import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.ErrorDataResult;
import com.hrms.core.utilities.results.SuccessDataResult;
import com.hrms.dataAccess.abstracts.WorkingTimeDao;
import com.hrms.entities.concretes.City;
import com.hrms.entities.concretes.WorkingTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingTimeManager implements WorkingTimeService {

    private WorkingTimeDao workingTimeDao;

    @Autowired
    public WorkingTimeManager(WorkingTimeDao workingTimeDao) {
        this.workingTimeDao = workingTimeDao;
    }


    @Override
    public DataResult<List<WorkingTime>> getAllWorkingTime() {
        return new SuccessDataResult<>(workingTimeDao.findAll(), "Data listed successfully");
    }

    @Override
    public DataResult<WorkingTime> findById(int id) {
        WorkingTime timeInDB = workingTimeDao.getOne(id);

        if (timeInDB == null) {
            return new ErrorDataResult<>("Data not found!");
        }

        return new SuccessDataResult<>(timeInDB, "Data found");
    }
}
