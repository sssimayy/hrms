package com.hrms.dataAccess.abstracts;

import com.hrms.entities.concretes.Cv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CvDao extends JpaRepository<Cv, Long> {

    List<Cv> findAllByIdOrderByExperienceDateDesc(Long id);

    //    @Query("select new com.hrms.entities.concretes.Cv"
//            + "(p.id,p.name,c.position)"
//            + "from Candidate  c INNER JOIN c.cv p")
    List<Cv> findAllByCandidateId(int id);
}
