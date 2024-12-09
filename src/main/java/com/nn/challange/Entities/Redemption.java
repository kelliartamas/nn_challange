package com.nn.challange.Entities;

import java.math.BigDecimal;
import java.util.logging.Logger;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Table(name = "SurValues")
public class Redemption {

    private static final Logger logger = Logger.getLogger(Redemption.class.getName());


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "Chdrnum", nullable = false, length = 8)
    private String chdrnum;

    @Column(name = "Survalue", nullable = false, precision = 15, scale = 2)
    private BigDecimal survalue;

    @Column(name = "Company", nullable = false, length = 1)
    private String company;

    @Column(name = "Currency", nullable = true, length = 3)
    private String currency;

    @Column(name = "ValidDate", nullable = true, length = 10)
    private String validDate;

    @PrePersist
    @PreUpdate
    public void validateFieldLengths() {
        validateFieldLength("chdrnum", chdrnum, 8);
        validateFieldLength("company", company, 1);
        validateFieldLength("currency", currency, 3);
        validateFieldLength("validDate", validDate, 10);
        
        // Validate BigDecimal precision and scale
        if (survalue != null) {
            if (survalue.scale() > 2) {
                logger.warning("Field 'survalue' exceeds the maximum scale of 2. Field value: " + survalue);
            }
            if (survalue.precision() > 15) {
                logger.warning("Field 'survalue' exceeds the maximum precision of 15. Field value: " + survalue);
            }
        }
    }

    private void validateFieldLength(String fieldName, String fieldValue, int maxLength) {
        if (fieldValue != null && fieldValue.length() > maxLength) {
            logger.warning("Field '" + fieldName + "' exceeds the maximum length of "
                               + maxLength + " characters. Field length: " + fieldValue.length() + " Field value: " + fieldValue);
        }
    }
}

