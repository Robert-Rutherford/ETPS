package com.etps.etps.repositories;

import com.etps.etps.models.Campus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Campuses extends JpaRepository<Campus, Long> {
    Campus findById(long id);

}
