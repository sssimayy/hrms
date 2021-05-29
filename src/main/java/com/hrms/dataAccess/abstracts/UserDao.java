package com.hrms.dataAccess.abstracts;

import com.hrms.entities.abstracts.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

}
