package com.j2ee.vol_uni_edu.models;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "group_recurring_donations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupRecurringDonation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private DonationGroup group;

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
    private Timestamp nextDonation;

    @PrePersist
    @PreUpdate
    protected void onCreateOrUpdate() {
        if (nextDonation == null) {
            nextDonation = new Timestamp(System.currentTimeMillis());
        }
    }
}
