package com.j2ee.vol_uni_edu.controller;

import com.j2ee.vol_uni_edu.models.Charity;
import com.j2ee.vol_uni_edu.models.UserRecurringDonation;
import com.j2ee.vol_uni_edu.repository.CharityRepository;
import com.j2ee.vol_uni_edu.services.UserRecurringDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/recurring-donations")
@CrossOrigin(origins = "http://localhost:3000")
public class UserRecurringDonationController {

    private final UserRecurringDonationService donationService;
    private final CharityRepository charityRepository;

    @Autowired
    public UserRecurringDonationController(UserRecurringDonationService donationService, CharityRepository charityRepository) {
        this.donationService = donationService;
        this.charityRepository = charityRepository;
    }
    @GetMapping
    public List<UserRecurringDonation> getallDonation(@PathVariable Long id) {
        return donationService.getAllDonations();
    }
    // Tạo hoặc cập nhật quyên góp định kỳ
    @PostMapping
    public ResponseEntity<UserRecurringDonation> createOrUpdateDonation(@RequestBody UserRecurringDonation donation) {
        UserRecurringDonation savedDonation = donationService.saveDonation(donation);
        Optional<Charity> charityOptional = charityRepository.findById(donation.getCampaign().getId());
        charityOptional.ifPresent(charity -> {
            BigDecimal newCurrentAmount = charity.getCurrentAmount().add(donation.getAmount());
            charity.setCurrentAmount(newCurrentAmount);
            charityRepository.save(charity);  // Lưu lại charity sau khi cập nhật
            // Kiểm tra xem current_amount có đạt hoặc vượt target_amount không
            if (newCurrentAmount.compareTo(charity.getTargetAmount()) >= 0) {
                charity.setStatus(Charity.CampaignStatus.COMPLETED); // Chuyển trạng thái thành 'completed'
            }
        });

        return new ResponseEntity<>(savedDonation, HttpStatus.CREATED);
    }

    // Lấy quyên góp định kỳ theo ID
    @GetMapping("/{id}")
    public ResponseEntity<UserRecurringDonation> getDonationById(@PathVariable Long id) {
        Optional<UserRecurringDonation> donation = donationService.getDonationById(id);
        return donation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // Lấy tất cả quyên góp định kỳ theo ID người dùng
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRecurringDonation>> getDonationsByUserId(@PathVariable Long userId) {
        List<UserRecurringDonation> donations = donationService.getDonationsByUserId(userId);
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }

    // Lấy tất cả quyên góp định kỳ theo ID chiến dịch
    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<UserRecurringDonation>> getDonationsByCampaignId(@PathVariable Long campaignId) {
        List<UserRecurringDonation> donations = donationService.getDonationsByCampaignId(campaignId);
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }

    // Xoá quyên góp định kỳ theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonationById(@PathVariable Long id) {
        if (donationService.getDonationById(id).isPresent()) {
            donationService.deleteDonationById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
