package com.j2ee.vol_uni_edu.repository;

import com.j2ee.vol_uni_edu.models.DonationGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DonationGroupRepository extends JpaRepository<DonationGroup, Long> {
    List<DonationGroup> findByOwnerId(Long ownerId);
    List<DonationGroup> findByCampaignId(Long campaignId);
}
