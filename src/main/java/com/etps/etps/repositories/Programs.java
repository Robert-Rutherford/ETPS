package com.etps.etps.repositories;

import com.etps.etps.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Programs extends JpaRepository<Program, Long> {
    Program findById(long id);


}
