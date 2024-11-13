package com.j2ee.vol_uni_edu.controller;

import com.j2ee.vol_uni_edu.models.SharedDonation;
import com.j2ee.vol_uni_edu.services.SharedDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shared-donations")
@CrossOrigin(origins = "http://localhost:3000")
public class SharedDonationController {

    @Autowired
    private SharedDonationService sharedDonationService;

    // Tạo hoặc cập nhật khoản quyên góp đã chia sẻ
    @PostMapping
    public ResponseEntity<SharedDonation> createOrUpdateSharedDonation(@RequestBody SharedDonation sharedDonation) {
        SharedDonation savedDonation = sharedDonationService.saveSharedDonation(sharedDonation);
        return new ResponseEntity<>(savedDonation, HttpStatus.CREATED);
    }

    // Lấy thông tin khoản quyên góp đã chia sẻ theo ID
    @GetMapping("/{id}")
    public ResponseEntity<SharedDonation> getSharedDonationById(@PathVariable Long id) {
        Optional<SharedDonation> sharedDonation = sharedDonationService.getSharedDonationById(id);
        return sharedDonation.map(donation -> new ResponseEntity<>(donation, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Lấy tất cả khoản quyên góp đã chia sẻ
    @GetMapping
    public List<SharedDonation> getAllSharedDonations() {
        return sharedDonationService.getAllSharedDonations();
    }

    // Xóa khoản quyên góp đã chia sẻ theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSharedDonation(@PathVariable Long id) {
        sharedDonationService.deleteSharedDonationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
