package com.hrms.dataAccess.abstracts;

import com.hrms.entities.concretes.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolDao extends JpaRepository<School, Long> {
}
