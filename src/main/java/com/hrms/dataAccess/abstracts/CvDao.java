package com.hrms.dataAccess.abstracts;

import com.hrms.entities.concretes.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CvDao extends JpaRepository<Cv, Integer> {

    List<Cv> findAllByPositionOrderByExperienceDateDesc(String position);

    List<Cv> findAllByUniversityNameOrderByGradDateDesc(String universityName);

    @Query("From Candidate where cv_id=:id")
    List<Cv> getCvInfoOfCandidate(int id);

}
