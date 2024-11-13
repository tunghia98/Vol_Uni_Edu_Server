package com.j2ee.vol_uni_edu.repository;

import com.j2ee.vol_uni_edu.models.GroupRecurringDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface GroupRecurringDonationRepository extends JpaRepository<GroupRecurringDonation, Long> {
    List<GroupRecurringDonation> findByGroupId(Long groupId);
    List<GroupRecurringDonation> findByCampaignId(Long campaignId);
    List<GroupRecurringDonation> findByNextDonationBetween(LocalDateTime startDate, LocalDateTime endDate);
    // Tổng donation theo ngày
    @Query("SELECT DATE(d.nextDonation) AS donationDate, SUM(d.amount) AS totalAmount " +
            "FROM GroupRecurringDonation d " +
            "WHERE d.nextDonation BETWEEN :startDate AND :endDate " +
            "GROUP BY DATE(d.nextDonation)")
    List<Object[]> getDailyTotalDonations(@Param("startDate") LocalDateTime startDate,
                                          @Param("endDate") LocalDateTime endDate);

    // Tổng donation theo tháng
    @Query("SELECT FUNCTION('MONTH', d.nextDonation) AS donationMonth, SUM(d.amount) AS totalAmount " +
            "FROM GroupRecurringDonation d " +
            "WHERE d.nextDonation BETWEEN :startDate AND :endDate " +
            "GROUP BY FUNCTION('MONTH', d.nextDonation)")
    List<Object[]> getMonthlyTotalDonations(@Param("startDate") LocalDateTime startDate,
                                            @Param("endDate") LocalDateTime endDate);

    // Tổng donation theo năm
    @Query("SELECT FUNCTION('YEAR', d.nextDonation) AS donationYear, SUM(d.amount) AS totalAmount " +
            "FROM GroupRecurringDonation d " +
            "WHERE d.nextDonation BETWEEN :startDate AND :endDate " +
            "GROUP BY FUNCTION('YEAR', d.nextDonation)")
    List<Object[]> getYearlyTotalDonations(@Param("startDate") LocalDateTime startDate,
                                           @Param("endDate") LocalDateTime endDate);
}
