package com.hrms.dataAccess.abstracts;

import com.hrms.entities.concretes.JobAdActivation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobAdActivationDao  extends JpaRepository<JobAdActivation,Integer> {
}
