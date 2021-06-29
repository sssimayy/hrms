package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.entities.concretes.Staff;
import com.hrms.entities.concretes.dtos.StaffUpdateDto;

import java.util.List;

public interface StaffService {
    public Result create(Staff staff);

    public DataResult<List<Staff>> getAll();

    public Result update(StaffUpdateDto staffUpdateDto);
}
