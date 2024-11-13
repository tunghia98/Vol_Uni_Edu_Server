package com.j2ee.vol_uni_edu.models;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "shared_campaigns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SharedCampaign implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private Charity campaign;

    @ManyToOne
    @JoinColumn(name = "shared_by", nullable = false)
    private User sharedBy;

    @Column(name = "shared_on", nullable = false)
    private Timestamp sharedOn;

    @Column(name = "status", nullable = false, length = 50)
    private String status = "active";

    @Column(name = "platform", nullable = false, length = 100)
    private String platform;

    @PrePersist
    protected void onCreate() {
        sharedOn = new Timestamp(System.currentTimeMillis());
    }
}
