package com.etps.etps.repositories;

import com.etps.etps.models.CampusAdditionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampusAdditionalInformation extends JpaRepository<CampusAdditionalInfo, Long> {
    CampusAdditionalInfo findById (long id);
}
