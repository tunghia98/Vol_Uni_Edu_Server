package com.j2ee.vol_uni_edu.repository;

import com.j2ee.vol_uni_edu.models.CampaignMedia;
import com.j2ee.vol_uni_edu.models.Charity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignMediaRepository extends JpaRepository<CampaignMedia, Long> {
    List<CampaignMedia> findByCampaign(Charity campaign);
}
