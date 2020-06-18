package com.etps.etps.repositories;

import com.etps.etps.models.ProviderFinancialContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderFinancialContacts extends JpaRepository<ProviderFinancialContact, Long> {
    ProviderFinancialContact findById (long id);
}
