package ru.diasoft.digitalq.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.diasoft.digitalq.controller.dto.SmsVerificationCheckRequest;
import ru.diasoft.digitalq.controller.dto.SmsVerificationCheckResponse;
import ru.diasoft.digitalq.controller.dto.SmsVerificationPostRequest;
import ru.diasoft.digitalq.controller.dto.SmsVerificationPostResponse;
import ru.diasoft.digitalq.domain.SmsVerification;
import ru.diasoft.digitalq.repository.SmsVerificationRepository;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
@Primary
public class SmsVerificationServiceImpl implements SmsVerificationService {

    private final SmsVerificationRepository smsVerificationRepository;

    @Override
    public SmsVerificationCheckResponse dsSmsVerificationCheck(SmsVerificationCheckRequest smsVerificationCheckRequest) {
        Optional<SmsVerification> smsVerification = smsVerificationRepository.findByGuidAndCode(
                smsVerificationCheckRequest.getProcessGUID(), smsVerificationCheckRequest.getCode());
        if (smsVerification.isPresent()) {
            return new SmsVerificationCheckResponse(true);
        } else {
            return new SmsVerificationCheckResponse(false);
        }
    }

    @Override
    public SmsVerificationPostResponse dsSmsVerificationCreate(SmsVerificationPostRequest smsVerificationPostRequest) {
        SmsVerification smsVerification = SmsVerification
                .builder()
                .code("OK")
                .guid(UUID.randomUUID().toString())
                .phoneNumber(smsVerificationPostRequest.getPhoneNumber())
                .build();
        SmsVerification smsVerificationInDb = smsVerificationRepository.save(smsVerification);
        return new SmsVerificationPostResponse(smsVerificationInDb.getGuid());
    }
}
