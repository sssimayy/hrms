package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.entities.concretes.Employer;
import com.hrms.entities.concretes.dtos.EmployerDto;

import java.util.List;

public interface EmployerService {

    Result add(EmployerDto employer);

    DataResult<List<Employer>> getAll();

    DataResult<Employer> findByEmail(String email);
}
