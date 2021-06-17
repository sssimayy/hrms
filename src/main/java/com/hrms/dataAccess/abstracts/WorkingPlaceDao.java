package com.hrms.dataAccess.abstracts;

import com.hrms.entities.concretes.WorkingPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingPlaceDao extends JpaRepository<WorkingPlace, Long> {
}
