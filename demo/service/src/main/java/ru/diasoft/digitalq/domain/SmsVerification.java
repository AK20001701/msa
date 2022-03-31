package ru.diasoft.digitalq.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "demo_smsverification")
public class SmsVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "demo_smsverification_id_seq")
    @SequenceGenerator(name = "demo_smsverification_id_seq", sequenceName = "demo_smsverification_id_seq", allocationSize = 1)
    @Column(name = "smsverificationid")
    private Long smsVerificationId;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "code")
    private String code;

    @Column(name = "guid")
    private String guid;
}
