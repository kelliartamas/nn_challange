package com.nn.challange.Entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.logging.Logger;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"outpayHeaderId"})
@Table(name = "OutPay_Header")
public class PayOut {

    private static final Logger logger = Logger.getLogger(PayOut.class.getName());
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Outpay_Header_ID", nullable = false)
    private int outpayHeaderId;

    @Column(name = "Clntnum", nullable = false, length = 8)
    private String clntnum;

    @Column(name = "Chdrnum", nullable = false, length = 8)
    private String chdrnum;

    @Column(name = "LetterType", nullable = false, length = 12)
    private String letterType;

    @Column(name = "PrintDate", nullable = false)
    private LocalDateTime printDate;

    @Column(name = "DataID", length = 6)
    private String dataID;

    @Column(name = "ClntName", length = 80)
    private String clntName;

    @Column(name = "ClntAddress", length = 80)
    private String clntAddress;

    @Column(name = "RegDate")
    private LocalDateTime regDate;

    @Column(name = "BenPercent", precision = 6, scale = 2)
    private BigDecimal benPercent;

    @Column(name = "Role1", length = 2)
    private String role1;

    @Column(name = "Role2", length = 2)
    private String role2;

    @Column(name = "CownNum", length = 8)
    private String cownNum;

    @Column(name = "CownName", length = 80)
    private String cownName;

    @Column(name = "Notice01", length = 80)
    private String notice01;

    @Column(name = "Notice02", length = 80)
    private String notice02;

    @Column(name = "Notice03", length = 80)
    private String notice03;

    @Column(name = "Notice04", length = 80)
    private String notice04;

    @Column(name = "Notice05", length = 80)
    private String notice05;

    @Column(name = "Notice06", length = 80)
    private String notice06;

    @Column(name = "Claim_ID", length = 9)
    private String claimID;

    @Column(name = "TP2ProcessDate")
    private LocalDateTime tp2ProcessDate;


    @PrePersist
    @PreUpdate
    public void validateFieldLengths() {
        validateFieldLength("clntnum", clntnum, 8);
        validateFieldLength("chdrnum", chdrnum, 8);
        validateFieldLength("letterType", letterType, 12);
        validateFieldLength("dataID", dataID, 6);
        validateFieldLength("clntName", clntName, 80);
        validateFieldLength("clntAddress", clntAddress, 80);
        validateFieldLength("role1", role1, 2);
        validateFieldLength("role2", role2, 2);
        validateFieldLength("cownNum", cownNum, 8);
        validateFieldLength("cownName", cownName, 80);
        validateFieldLength("notice01", notice01, 80);
        validateFieldLength("notice02", notice02, 80);
        validateFieldLength("notice03", notice03, 80);
        validateFieldLength("notice04", notice04, 80);
        validateFieldLength("notice05", notice05, 80);
        validateFieldLength("notice06", notice06, 80);
        validateFieldLength("claimID", claimID, 9);
        
        // Validate BigDecimal precision and scale
        if (benPercent != null) {
            if (benPercent.scale() > 2) {
                logger.warning("Field 'benPercent' exceeds the maximum scale of 2. Field value: " + benPercent);
            }
            if (benPercent.precision() > 6) {
                logger.warning("Field 'benPercent' exceeds the maximum precision of 6. Field value: " + benPercent);
            }
        }
    }

    private void validateFieldLength(String fieldName, String fieldValue, int maxLength) {
        if (fieldValue != null && fieldValue.length() > maxLength) {
            logger.warning("Field '" + fieldName + "' exceeds the maximum length of " + maxLength + " characters. Field length: " + fieldValue.length() + " Field value: " + fieldValue);
        }
    }
}
