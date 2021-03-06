package com.hrms.business.concretes;

import com.hrms.business.abstracts.WorkingPlaceService;
import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.ErrorDataResult;
import com.hrms.core.utilities.results.SuccessDataResult;
import com.hrms.dataAccess.abstracts.WorkingPlaceDao;
import com.hrms.entities.concretes.City;
import com.hrms.entities.concretes.WorkingPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingPlaceManager implements WorkingPlaceService {

    private WorkingPlaceDao workingPlaceDao;

    @Autowired
    public WorkingPlaceManager(WorkingPlaceDao workingPlaceDao) {
        this.workingPlaceDao = workingPlaceDao;
    }

    @Override
    public DataResult<List<WorkingPlace>> getAllWorkingPlace() {
        return new SuccessDataResult<>(workingPlaceDao.findAll(), "Data listed successfully");
    }

    @Override
    public DataResult<WorkingPlace> getById(int id) {
        WorkingPlace placeInDB = workingPlaceDao.getOne(id);

        if (placeInDB == null) {
            return new ErrorDataResult<>("Data not found!");
        }

        return new SuccessDataResult<>(placeInDB, "Data found");    }
}
