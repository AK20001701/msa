package ru.diasoft.digitalq.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.digitalq.controller.dto.SmsVerificationCheckRequest;
import ru.diasoft.digitalq.controller.dto.SmsVerificationCheckResponse;
import ru.diasoft.digitalq.controller.dto.SmsVerificationPostRequest;
import ru.diasoft.digitalq.controller.dto.SmsVerificationPostResponse;
import ru.diasoft.digitalq.domain.SmsVerification;
import ru.diasoft.digitalq.repository.SmsVerificationRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("Класс SmsVerificationServiceImplTest должен")
@ExtendWith(MockitoExtension.class)
public class SmsVerificationServiceImplTest {

    private SmsVerificationServiceImpl smsVerificationService;

    @Mock
    private SmsVerificationRepository repository;

    private final String PHONE_NUMBER = "89998887766";
    private final String CODE = "7777";
    private final String GUID = "97141f2a-c398-4760-86e4-170b0939e3e4";

    @BeforeEach
    void setUp() {
        smsVerificationService = new SmsVerificationServiceImpl(repository);
    }

    @DisplayName("Подтвердит наличие смс подтверждения")
    @Test
    void dsSmsVerificationCheck() {
        SmsVerificationCheckRequest smsVerificationCheckRequest = new SmsVerificationCheckRequest(GUID, CODE);
        SmsVerification smsVerification = SmsVerification
                .builder()
                .code(CODE)
                .guid(GUID)
                .build();
        when(repository.findByGuidAndCode(GUID, CODE)).thenReturn(Optional.of(smsVerification));
        SmsVerificationCheckResponse result = smsVerificationService.dsSmsVerificationCheck(smsVerificationCheckRequest);
        assertThat(result.getCheckResult()).isEqualTo(true);
    }

    @DisplayName("Не подтвердит наличие смс подтверждения")
    @Test
    void dsSmsVerificationCheckFalse() {
        SmsVerificationCheckRequest smsVerificationCheckRequest = new SmsVerificationCheckRequest(GUID, CODE);
        when(repository.findByGuidAndCode(GUID, CODE)).thenReturn(Optional.empty());
        SmsVerificationCheckResponse result = smsVerificationService.dsSmsVerificationCheck(smsVerificationCheckRequest);
        assertThat(result.getCheckResult()).isEqualTo(false);
    }

    @DisplayName("Создавать подтверждение об смс")
    @Test
    void dsSmsVerificationCreate() {
        SmsVerificationPostRequest smsVerificationPostRequest = new SmsVerificationPostRequest(PHONE_NUMBER);
        SmsVerification smsVerification = SmsVerification
                .builder()
                .code(CODE)
                .guid(GUID)
                .build();
        when(repository.save(any())).thenReturn(smsVerification);
        SmsVerificationPostResponse result = smsVerificationService.dsSmsVerificationCreate(smsVerificationPostRequest);
        verify(repository).save(any());
        assertThat(result.getProcessGUID()).isNotNull();
    }

}
