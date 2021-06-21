package com.hrms.dataAccess.abstracts;

import com.hrms.entities.concretes.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateDao extends JpaRepository<Candidate, Long> {

    boolean existsByNationalIdentity(String nationalIdentity);

    Candidate getById(int id);

    boolean existsByEmail(String email);

}
