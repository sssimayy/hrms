package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.entities.concretes.WorkingPlace;

import java.util.List;

public interface WorkingPlaceService {
    DataResult<List<WorkingPlace>> getAllWorkingPlace();

    DataResult<WorkingPlace> getById(int id);
}
