package ru.diasoft.digitalq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diasoft.digitalq.domain.SmsVerification;

import java.util.Optional;

public interface SmsVerificationRepository extends JpaRepository<SmsVerification, Long> {
    Optional<SmsVerification> findByGuidAndCode(String guid, String code);
}
