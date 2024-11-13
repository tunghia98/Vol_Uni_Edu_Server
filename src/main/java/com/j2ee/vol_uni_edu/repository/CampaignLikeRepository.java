package com.j2ee.vol_uni_edu.repository;

import com.j2ee.vol_uni_edu.models.CampaignLike;
import com.j2ee.vol_uni_edu.models.Charity;
import com.j2ee.vol_uni_edu.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CampaignLikeRepository extends JpaRepository<CampaignLike, Long> {
    List<CampaignLike> findByCampaign(Charity campaign);
    List<CampaignLike> findByUser(User user);
    Optional<CampaignLike> findByCampaignAndUser(Charity campaign, User user);
    long countByCampaign(Charity campaign);
}
