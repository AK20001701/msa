package ru.diasoft.digitalq.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.digitalq.domain.SmsVerification;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SmsVerificationRepositoryTest {

    @Autowired
    private SmsVerificationRepository repository;

    private final String PHONE_NUMBER = "87776665522";
    private final String CODE = "7777";

    @Test
    public void smsVerificationCreateTest() {
        String guid = UUID.randomUUID().toString();
        SmsVerification smsVerification = SmsVerification.builder()
                .guid(guid)
                .phoneNumber(PHONE_NUMBER)
                .code(CODE)
                .build();
        SmsVerification createdEntity = repository.save(smsVerification);
        assertThat(smsVerification).isEqualToComparingOnlyGivenFields(createdEntity, "phoneNumber");
        assertThat(smsVerification).isEqualToComparingOnlyGivenFields(createdEntity, "guid");
        assertThat(smsVerification).isEqualToComparingOnlyGivenFields(createdEntity, "code");
    }

    @Test
    public void findByGuidAndCode() {
        String guid = UUID.randomUUID().toString();

        SmsVerification smsVerification =
                SmsVerification.builder()
                        .guid(guid)
                        .phoneNumber(PHONE_NUMBER)
                        .code(CODE)
                        .build();

        SmsVerification createdEntity = repository.save(smsVerification);

        Optional<SmsVerification> foundEntity = repository.findByGuidAndCode(guid, CODE);
        assertTrue(foundEntity.isPresent());
        assertEquals(createdEntity, foundEntity.orElse(SmsVerification.builder().build()));
    }
}
