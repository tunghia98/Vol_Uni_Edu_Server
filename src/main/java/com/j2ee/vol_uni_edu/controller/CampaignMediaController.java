package com.j2ee.vol_uni_edu.controller;

import com.j2ee.vol_uni_edu.models.CampaignMedia;
import com.j2ee.vol_uni_edu.models.Charity;
import com.j2ee.vol_uni_edu.services.CampaignMediaService;
import com.j2ee.vol_uni_edu.services.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/media")
public class CampaignMediaController {

    @Autowired
    private CampaignMediaService mediaService;

    @Autowired
    private CharityService charityService;

    // Add new media to a campaign
    @PostMapping("/{campaignId}")
    public ResponseEntity<CampaignMedia> addMedia(
            @PathVariable Long campaignId, @RequestBody CampaignMedia media) {

        Optional<Charity> campaignOpt = charityService.getCharityById(campaignId);

        if (campaignOpt.isPresent()) {
            media.setCampaign(campaignOpt.get());
            CampaignMedia savedMedia = mediaService.addMedia(media);
            return new ResponseEntity<>(savedMedia, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all media for a specific campaign
    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<CampaignMedia>> getMediaByCampaign(@PathVariable Long campaignId) {
        Optional<Charity> campaignOpt = charityService.getCharityById(campaignId);
        if (campaignOpt.isPresent()) {
            List<CampaignMedia> media = mediaService.getMediaByCampaign(campaignOpt.get());
            return new ResponseEntity<>(media, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete media by ID
    @DeleteMapping("/{mediaId}")
    public ResponseEntity<Void> deleteMedia(@PathVariable Long mediaId) {
        Optional<CampaignMedia> mediaOpt = mediaService.getMediaById(mediaId);
        if (mediaOpt.isPresent()) {
            mediaService.deleteMedia(mediaId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
