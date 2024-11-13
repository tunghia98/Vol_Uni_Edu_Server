package com.j2ee.vol_uni_edu.services;

import com.j2ee.vol_uni_edu.models.CampaignLike;
import com.j2ee.vol_uni_edu.models.Charity;
import com.j2ee.vol_uni_edu.models.User;
import com.j2ee.vol_uni_edu.repository.CampaignLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignLikeService {

    private final CampaignLikeRepository campaignLikeRepository;

    @Autowired
    public CampaignLikeService(CampaignLikeRepository campaignLikeRepository) {
        this.campaignLikeRepository = campaignLikeRepository;
    }

    public CampaignLike likeCampaign(Charity campaign, User user) {
        return campaignLikeRepository.save(new CampaignLike(null, campaign, user, null));
    }

    public void unlikeCampaign(Charity campaign, User user) {
        campaignLikeRepository.findByCampaignAndUser(campaign, user)
                .ifPresent(campaignLikeRepository::delete);
    }

    public List<CampaignLike> getLikesByCampaign(Charity campaign) {
        return campaignLikeRepository.findByCampaign(campaign);
    }

    public List<CampaignLike> getLikesByUser(User user) {
        return campaignLikeRepository.findByUser(user);
    }

    public long countLikesByCampaign(Charity campaign) {
        return campaignLikeRepository.countByCampaign(campaign);
    }

    public Optional<CampaignLike> getCampaignLikeByCampaignAndUser(Charity campaign, User user) {
        return campaignLikeRepository.findByCampaignAndUser(campaign, user);
    }
}
