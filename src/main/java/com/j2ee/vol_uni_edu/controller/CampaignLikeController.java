package com.j2ee.vol_uni_edu.controller;

import com.j2ee.vol_uni_edu.models.CampaignLike;
import com.j2ee.vol_uni_edu.models.Charity;
import com.j2ee.vol_uni_edu.models.User;
import com.j2ee.vol_uni_edu.services.CampaignLikeService;
import com.j2ee.vol_uni_edu.services.CharityService;
import com.j2ee.vol_uni_edu.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaign-likes")
@CrossOrigin(origins = "http://localhost:3000")
public class CampaignLikeController {

    private final CampaignLikeService campaignLikeService;
    private final CharityService charityService;
    private final UserService userService;

    @Autowired
    public CampaignLikeController(CampaignLikeService campaignLikeService, CharityService charityService, UserService userService) {
        this.campaignLikeService = campaignLikeService;
        this.charityService = charityService;
        this.userService = userService;
    }

    @PostMapping("/{campaignId}/{userId}")
    public ResponseEntity<CampaignLike> likeCampaign(@PathVariable Long campaignId, @PathVariable Long userId) {
        Charity campaign = charityService.getCharityById(campaignId).orElseThrow(() -> new RuntimeException("Campaign not found"));
        User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        CampaignLike campaignLike = campaignLikeService.likeCampaign(campaign, user);
        return new ResponseEntity<>(campaignLike, HttpStatus.CREATED);
    }

    @DeleteMapping("/{campaignId}/{userId}")
    public ResponseEntity<Void> unlikeCampaign(@PathVariable Long campaignId, @PathVariable Long userId) {
        Charity campaign = charityService.getCharityById(campaignId).orElseThrow(() -> new RuntimeException("Campaign not found"));
        User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        campaignLikeService.unlikeCampaign(campaign, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<CampaignLike>> getLikesByCampaign(@PathVariable Long campaignId) {
        Charity campaign = charityService.getCharityById(campaignId).orElseThrow(() -> new RuntimeException("Campaign not found"));
        List<CampaignLike> likes = campaignLikeService.getLikesByCampaign(campaign);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    @GetMapping("/campaign/{campaignId}/count")
    public ResponseEntity<Long> countLikesByCampaign(@PathVariable Long campaignId) {
        Charity campaign = charityService.getCharityById(campaignId).orElseThrow(() -> new RuntimeException("Campaign not found"));
        long count = campaignLikeService.countLikesByCampaign(campaign);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
