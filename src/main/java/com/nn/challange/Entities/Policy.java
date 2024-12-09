package com.nn.challange.Entities;

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
@Table(name = "Policy")
public class Policy {

    private static final Logger logger = Logger.getLogger(Policy.class.getName());

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "Chdrnum", nullable = false, length = 8)
    private String chdrnum;

    @Column(name = "Cownnum", nullable = false, length = 8)
    private String cownnum;

    @Column(name = "OwnerName", length = 50)
    private String ownerName;

    @Column(name = "LifcNum", length = 8)
    private String lifcNum;

    @Column(name = "LifcName", length = 50)
    private String lifcName;

    @Column(name = "Aracde", length = 3)
    private String aracde;

    @Column(name = "Agntnum", length = 5)
    private String agntnum;

    @Column(name = "MailAddress", length = 50)
    private String mailAddress;

    @PrePersist
    @PreUpdate
    public void validateFieldLengths() {
        validateFieldLength("chdrnum", chdrnum, 8);
        validateFieldLength("cownnum", cownnum, 8);
        validateFieldLength("ownerName", ownerName, 50);
        validateFieldLength("lifcNum", lifcNum, 8);
        validateFieldLength("lifcName", lifcName, 50);
        validateFieldLength("aracde", aracde, 3);
        validateFieldLength("agntnum", agntnum, 5);
        validateFieldLength("mailAddress", mailAddress, 50);
    }

    
    private void validateFieldLength(String fieldName, String fieldValue, int maxLength) {
        if (fieldValue != null && fieldValue.length() > maxLength) {
            logger.warning("Field '" + fieldName + "' exceeds the maximum length of "
                               + maxLength + " characters. Field length: " + fieldValue.length() + " Field value: " + fieldValue);
        }
    }
}
