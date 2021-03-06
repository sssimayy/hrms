package com.hrms.business.concretes;

import com.hrms.business.abstracts.CityService;
import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.ErrorDataResult;
import com.hrms.core.utilities.results.SuccessDataResult;
import com.hrms.dataAccess.abstracts.CityDao;
import com.hrms.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityManager implements CityService {

    private CityDao cityDao;

    @Autowired
    public CityManager(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<List<City>>(this.cityDao.findAll());
    }

    @Override
    public DataResult<City> findById(int id) {
        City cityInDB = cityDao.getOne(id);

        if (cityInDB == null) {
            return new ErrorDataResult<>("Data not found!");
        }

        return new SuccessDataResult<>(cityInDB, "Data found");
    }
}
