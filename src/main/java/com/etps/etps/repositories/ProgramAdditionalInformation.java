package com.etps.etps.repositories;

import com.etps.etps.models.ProgramAdditionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramAdditionalInformation extends JpaRepository<ProgramAdditionalInfo, Long> {
    ProgramAdditionalInfo findById (long id);
}
