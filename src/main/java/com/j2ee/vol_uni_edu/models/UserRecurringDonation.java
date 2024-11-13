package com.j2ee.vol_uni_edu.models;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_recurring_donations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRecurringDonation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private Charity campaign;

    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "frequency", nullable = false, length = 50)
    private String frequency;

    @Column(name = "status", nullable = false, length = 50)
    private String status = "active";

    @Column(name = "next_donation", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime nextDonation;

    @PrePersist
    @PreUpdate
    protected void onCreateOrUpdate() {
        if (nextDonation == null) {
            nextDonation = LocalDateTime.now();
        }
    }
}
