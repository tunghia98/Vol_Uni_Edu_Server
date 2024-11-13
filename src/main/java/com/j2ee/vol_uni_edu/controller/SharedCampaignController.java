package com.j2ee.vol_uni_edu.controller;

import com.j2ee.vol_uni_edu.models.SharedCampaign;
import com.j2ee.vol_uni_edu.services.SharedCampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shared-campaigns")
@CrossOrigin(origins = "http://localhost:3000")
public class SharedCampaignController {

    @Autowired
    private SharedCampaignService sharedCampaignService;

    // Tạo hoặc cập nhật chiến dịch đã chia sẻ
    @PostMapping
    public ResponseEntity<SharedCampaign> createOrUpdateSharedCampaign(@RequestBody SharedCampaign sharedCampaign) {
        SharedCampaign savedCampaign = sharedCampaignService.saveSharedCampaign(sharedCampaign);
        return new ResponseEntity<>(savedCampaign, HttpStatus.CREATED);
    }

    // Lấy thông tin chiến dịch đã chia sẻ theo ID
    @GetMapping("/{id}")
    public ResponseEntity<SharedCampaign> getSharedCampaignById(@PathVariable Long id) {
        Optional<SharedCampaign> sharedCampaign = sharedCampaignService.getSharedCampaignById(id);
        return sharedCampaign.map(campaign -> new ResponseEntity<>(campaign, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Lấy tất cả chiến dịch đã chia sẻ
    @GetMapping
    public List<SharedCampaign> getAllSharedCampaigns() {
        return sharedCampaignService.getAllSharedCampaigns();
    }

    // Xóa chiến dịch đã chia sẻ theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSharedCampaign(@PathVariable Long id) {
        sharedCampaignService.deleteSharedCampaignById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
