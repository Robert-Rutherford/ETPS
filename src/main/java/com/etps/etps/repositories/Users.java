package com.etps.etps.repositories;

import com.etps.etps.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Users extends JpaRepository<User, Long> {
    User findById(long id);
    User findByUsername(String username);
    User findByUserProviderId(long id);

}
