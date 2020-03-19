package com.etps.etps.repositories;

import com.etps.etps.models.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Submissions extends JpaRepository<Submission, Long> {
    Submission findById(long id);
//    Submission findAllByProviderAndStatus(Provider provider, String status);
//    Submission findAllByStatus(String status);
//    Submission findAllByProvider_Id(long id);
//    Submission findByProgram_Id(long id);

}
