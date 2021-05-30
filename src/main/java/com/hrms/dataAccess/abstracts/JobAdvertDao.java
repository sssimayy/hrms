package com.hrms.dataAccess.abstracts;

import com.hrms.entities.concretes.JobAdvert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobAdvertDao extends JpaRepository<JobAdvert,Integer> {
    @Query("From JobAdvert where isOpen = true")
    List<JobAdvert> getAllOpenJobAdvertList();

    @Query("From JobAdvert where isOpen = true Order By publishedAt Desc")
    List<JobAdvert> findAllByOrderByPublishedAtDesc();

    @Query("From JobAdvert where isOpen = true and employer_id =:id")
    List<JobAdvert> getAllOpenJobAdvertByEmployer(int id);
}
