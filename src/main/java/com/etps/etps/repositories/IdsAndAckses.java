package com.etps.etps.repositories;

import com.etps.etps.models.AdditionalIDsAndAcks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdsAndAckses extends JpaRepository<AdditionalIDsAndAcks, Long> {
    AdditionalIDsAndAcks findById(long id);
}
