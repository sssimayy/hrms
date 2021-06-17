package com.hrms.dataAccess.abstracts;

import com.hrms.entities.concretes.JobAdvert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobAdvertDao extends JpaRepository<JobAdvert, Integer> {

    List<JobAdvert> findAllByIsActive(boolean isActive);

    List<JobAdvert> findAllByIsActiveOrderByPublishedAtDesc(boolean isActive);

    List<JobAdvert> findAllByJobId(int id);

    @Query("From JobAdvert where isActive=true and id=:id")
    List<JobAdvert> getEmployersActiveAdvert(int id);

}
