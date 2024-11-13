package com.j2ee.vol_uni_edu.repository;

import com.j2ee.vol_uni_edu.models.CampaignComment;
import com.j2ee.vol_uni_edu.models.Charity;
import com.j2ee.vol_uni_edu.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignCommentRepository extends JpaRepository<CampaignComment, Long> {
    List<CampaignComment> findByCampaign(Charity campaign);
    List<CampaignComment> findByUser(User user);
}
