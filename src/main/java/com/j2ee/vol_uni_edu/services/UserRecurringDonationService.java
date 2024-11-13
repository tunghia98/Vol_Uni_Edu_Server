package com.j2ee.vol_uni_edu.services;

import com.j2ee.vol_uni_edu.models.UserRecurringDonation;
import com.j2ee.vol_uni_edu.repository.UserRecurringDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserRecurringDonationService {

    private final UserRecurringDonationRepository donationRepository;

    @Autowired
    public UserRecurringDonationService(UserRecurringDonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    // Tạo hoặc cập nhật quyên góp định kỳ
    public UserRecurringDonation saveDonation(UserRecurringDonation donation) {
        if (donation.getFrequency() == null || donation.getFrequency().isEmpty()) {
            donation.setFrequency("");  // Thiết lập frequency thành chuỗi rỗng nếu giá trị ban đầu là null hoặc rỗng
        }
        return donationRepository.save(donation);
    }

    // Lấy quyên góp định kỳ theo ID
    public Optional<UserRecurringDonation> getDonationById(Long id) {
        return donationRepository.findById(id);
    }

    // Lấy danh sách quyên góp theo người dùng
    public List<UserRecurringDonation> getDonationsByUserId(Long userId) {
        return donationRepository.findByUserId(userId);
    }

    // Lấy danh sách quyên góp theo chiến dịch
    public List<UserRecurringDonation> getDonationsByCampaignId(Long campaignId) {
        return donationRepository.findByCampaignId(campaignId);
    }
    public List<UserRecurringDonation> getDonationsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return donationRepository.findByNextDonationBetween(startDate, endDate);
    }
    public List<UserRecurringDonation> getAllDonations() {
        return donationRepository.findAll();
    }

    public List<Object[]> getUserDailyTotalDonations(LocalDateTime startDate, LocalDateTime endDate) {
        return donationRepository.getDailyTotalDonations(startDate, endDate);
    }
    public List<Object[]> getUserMonthlyTotalDonations(LocalDateTime startDate, LocalDateTime endDate) {
        return donationRepository.getMonthlyTotalDonations(startDate, endDate);
    }
    public List<Object[]> getUserYearlyTotalDonations(LocalDateTime startDate, LocalDateTime endDate) {
        return donationRepository.getYearlyTotalDonations(startDate, endDate);
    }
    // Xoá quyên góp định kỳ theo ID
    public void deleteDonationById(Long id) {
        donationRepository.deleteById(id);
    }
}
