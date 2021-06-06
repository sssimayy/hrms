package com.hrms.dataAccess.abstracts;

import com.hrms.entities.concretes.Candidate;
import com.hrms.entities.concretes.Cv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateDao extends JpaRepository<Candidate, Long> {

    boolean existsByNationalIdentity(String nationalIdentity);
//    List<Cv> findAllByName(String name);

}
