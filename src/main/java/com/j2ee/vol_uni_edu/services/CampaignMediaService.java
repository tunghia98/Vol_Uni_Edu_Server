package com.j2ee.vol_uni_edu.services;

import com.j2ee.vol_uni_edu.models.CampaignMedia;
import com.j2ee.vol_uni_edu.models.Charity;
import com.j2ee.vol_uni_edu.repository.CampaignMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignMediaService {

    @Autowired
    private CampaignMediaRepository mediaRepository;

    // Add new media
    public CampaignMedia addMedia(CampaignMedia media) {
        return mediaRepository.save(media);
    }

    // Get all media by campaign
    public List<CampaignMedia> getMediaByCampaign(Charity campaign) {
        return mediaRepository.findByCampaign(campaign);
    }

    // Get media by ID
    public Optional<CampaignMedia> getMediaById(Long id) {
        return mediaRepository.findById(id);
    }

    // Delete media by ID
    public void deleteMedia(Long id) {
        mediaRepository.deleteById(id);
    }
}
