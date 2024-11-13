package com.j2ee.vol_uni_edu.services;

import com.j2ee.vol_uni_edu.models.GroupRecurringDonation;
import com.j2ee.vol_uni_edu.repository.GroupRecurringDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GroupRecurringDonationService {

    private final GroupRecurringDonationRepository donationRepository;

    @Autowired
    public GroupRecurringDonationService(GroupRecurringDonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    // Tạo hoặc cập nhật khoản quyên góp định kỳ
    public GroupRecurringDonation saveGroupRecurringDonation(GroupRecurringDonation donation) {
        if (donation.getFrequency() == null || donation.getFrequency().isEmpty()) {
            donation.setFrequency("");  // Thiết lập frequency thành chuỗi rỗng nếu giá trị ban đầu là null hoặc rỗng
        }
        return donationRepository.save(donation);
    }

    // Lấy khoản quyên góp định kỳ theo ID
    public Optional<GroupRecurringDonation> getGroupRecurringDonationById(Long id) {
        return donationRepository.findById(id);
    }

    // Lấy tất cả khoản quyên góp định kỳ theo nhóm
    public List<GroupRecurringDonation> getGroupRecurringDonationsByGroupId(Long groupId) {
        return donationRepository.findByGroupId(groupId);
    }

    // Lấy tất cả khoản quyên góp định kỳ theo chiến dịch
    public List<GroupRecurringDonation> getGroupRecurringDonationsByCampaignId(Long campaignId) {
        return donationRepository.findByCampaignId(campaignId);
    }
    public List<GroupRecurringDonation> getDonationsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return donationRepository.findByNextDonationBetween(startDate, endDate);
    }
    public List<Object[]> getDailyTotalDonations(LocalDateTime startDate, LocalDateTime endDate) {
        return donationRepository.getDailyTotalDonations(startDate, endDate);
    }
    public List<Object[]> getGroupDailyTotalDonations(LocalDateTime startDate, LocalDateTime endDate) {
        return donationRepository.getDailyTotalDonations(startDate, endDate);
    }
    public List<Object[]> getGroupMonthlyTotalDonations(LocalDateTime startDate, LocalDateTime endDate) {
        return donationRepository.getMonthlyTotalDonations(startDate, endDate);
    }
    public List<Object[]> getGroupYearlyTotalDonations(LocalDateTime startDate, LocalDateTime endDate) {
        return donationRepository.getYearlyTotalDonations(startDate, endDate);
    }
    // Xoá khoản quyên góp định kỳ theo ID
    public void deleteGroupRecurringDonationById(Long id) {
        donationRepository.deleteById(id);
    }

}
