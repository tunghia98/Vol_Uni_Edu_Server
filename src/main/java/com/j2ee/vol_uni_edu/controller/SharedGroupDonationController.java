package com.j2ee.vol_uni_edu.controller;

import com.j2ee.vol_uni_edu.models.SharedGroupDonation;
import com.j2ee.vol_uni_edu.services.SharedGroupDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shared-group-donations")
@CrossOrigin(origins = "http://localhost:3000")
public class SharedGroupDonationController {

    @Autowired
    private SharedGroupDonationService sharedGroupDonationService;

    // Tạo hoặc cập nhật khoản quyên góp nhóm đã chia sẻ
    @PostMapping
    public ResponseEntity<SharedGroupDonation> createOrUpdateSharedGroupDonation(@RequestBody SharedGroupDonation sharedGroupDonation) {
        SharedGroupDonation savedDonation = sharedGroupDonationService.saveSharedGroupDonation(sharedGroupDonation);
        return new ResponseEntity<>(savedDonation, HttpStatus.CREATED);
    }

    // Lấy thông tin khoản quyên góp nhóm đã chia sẻ theo ID
    @GetMapping("/{id}")
    public ResponseEntity<SharedGroupDonation> getSharedGroupDonationById(@PathVariable Long id) {
        Optional<SharedGroupDonation> sharedGroupDonation = sharedGroupDonationService.getSharedGroupDonationById(id);
        return sharedGroupDonation.map(donation -> new ResponseEntity<>(donation, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Lấy tất cả khoản quyên góp nhóm đã chia sẻ
    @GetMapping
    public List<SharedGroupDonation> getAllSharedGroupDonations() {
        return sharedGroupDonationService.getAllSharedGroupDonations();
    }

    // Xóa khoản quyên góp nhóm đã chia sẻ theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSharedGroupDonation(@PathVariable Long id) {
        sharedGroupDonationService.deleteSharedGroupDonationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
