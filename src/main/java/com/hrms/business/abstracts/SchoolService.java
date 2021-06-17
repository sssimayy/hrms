package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.entities.concretes.School;

import java.util.List;

public interface SchoolService {
    DataResult<List<School>> getAllSchool();
}
