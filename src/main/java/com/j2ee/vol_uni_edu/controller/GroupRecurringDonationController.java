package com.j2ee.vol_uni_edu.controller;

import com.j2ee.vol_uni_edu.models.Charity;
import com.j2ee.vol_uni_edu.models.GroupRecurringDonation;
import com.j2ee.vol_uni_edu.repository.CharityRepository;
import com.j2ee.vol_uni_edu.services.GroupRecurringDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/group-recurring-donations")
@CrossOrigin(origins = "http://localhost:3000")
public class GroupRecurringDonationController {

    private final GroupRecurringDonationService donationService;
    private final CharityRepository charityRepository;

    @Autowired
    public GroupRecurringDonationController(GroupRecurringDonationService donationService, CharityRepository charityRepository) {
        this.donationService = donationService;
        this.charityRepository = charityRepository;
    }

    // Tạo hoặc cập nhật khoản quyên góp định kỳ
    @PostMapping
    public ResponseEntity<GroupRecurringDonation> createOrUpdateGroupRecurringDonation(@RequestBody GroupRecurringDonation donation) {
        GroupRecurringDonation savedDonation = donationService.saveGroupRecurringDonation(donation);
        // Cập nhật current_amount của Charity
        Optional<Charity> charityOptional = charityRepository.findById(donation.getCampaign().getId());
        charityOptional.ifPresent(charity -> {
            // Cộng thêm số tiền quyên góp vào current_amount
            BigDecimal newCurrentAmount = charity.getCurrentAmount().add(donation.getAmount());
            charity.setCurrentAmount(newCurrentAmount);

            // Kiểm tra xem current_amount có đạt hoặc vượt target_amount không
            if (newCurrentAmount.compareTo(charity.getTargetAmount()) >= 0) {
                charity.setStatus(Charity.CampaignStatus.COMPLETED); // Chuyển trạng thái thành 'completed'
            }

            // Lưu lại charity sau khi cập nhật
            charityRepository.save(charity);
        });

        return new ResponseEntity<>(savedDonation, HttpStatus.CREATED);
    }

    // Lấy khoản quyên góp định kỳ theo ID
    @GetMapping("/{id}")
    public ResponseEntity<GroupRecurringDonation> getGroupRecurringDonationById(@PathVariable Long id) {
        Optional<GroupRecurringDonation> donation = donationService.getGroupRecurringDonationById(id);
        return donation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Lấy tất cả khoản quyên góp định kỳ theo nhóm
    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<GroupRecurringDonation>> getGroupRecurringDonationsByGroupId(@PathVariable Long groupId) {
        List<GroupRecurringDonation> donations = donationService.getGroupRecurringDonationsByGroupId(groupId);
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }

    // Lấy tất cả khoản quyên góp định kỳ theo chiến dịch
    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<GroupRecurringDonation>> getGroupRecurringDonationsByCampaignId(@PathVariable Long campaignId) {
        List<GroupRecurringDonation> donations = donationService.getGroupRecurringDonationsByCampaignId(campaignId);
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }

    // Xoá khoản quyên góp định kỳ theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupRecurringDonationById(@PathVariable Long id) {
        if (donationService.getGroupRecurringDonationById(id).isPresent()) {
            donationService.deleteGroupRecurringDonationById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
