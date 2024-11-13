package com.j2ee.vol_uni_edu.controller;

import com.j2ee.vol_uni_edu.models.DonationGroup;
import com.j2ee.vol_uni_edu.services.DonationGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/donation-groups")
@CrossOrigin(origins = "http://localhost:3000")
public class DonationGroupController {

    private final DonationGroupService groupService;

    @Autowired
    public DonationGroupController(DonationGroupService groupService) {
        this.groupService = groupService;
    }

    // Tạo hoặc cập nhật nhóm quyên góp
    @PostMapping
    public ResponseEntity<DonationGroup> createOrUpdateDonationGroup(@RequestBody DonationGroup group) {
        DonationGroup savedGroup = groupService.saveDonationGroup(group);
        return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
    }
    // Lấy tất cả nhóm quyên góp
    @GetMapping
    public ResponseEntity<List<DonationGroup>> getAllDonationGroups() {
        List<DonationGroup> groups = groupService.getAllDonationGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }
    // Lấy nhóm quyên góp theo ID
    @GetMapping("/{id}")
    public ResponseEntity<DonationGroup> getDonationGroupById(@PathVariable Long id) {
        Optional<DonationGroup> group = groupService.getDonationGroupById(id);
        return group.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Lấy tất cả các nhóm quyên góp theo ID người sở hữu
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<DonationGroup>> getDonationGroupsByOwnerId(@PathVariable Long ownerId) {
        List<DonationGroup> groups = groupService.getDonationGroupsByOwnerId(ownerId);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // Lấy tất cả các nhóm quyên góp theo ID chiến dịch
    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<DonationGroup>> getDonationGroupsByCampaignId(@PathVariable Long campaignId) {
        List<DonationGroup> groups = groupService.getDonationGroupsByCampaignId(campaignId);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    // Xóa nhóm quyên góp theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonationGroupById(@PathVariable Long id) {
        if (groupService.getDonationGroupById(id).isPresent()) {
            groupService.deleteDonationGroupById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
