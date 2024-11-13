package com.j2ee.vol_uni_edu.controller;

import com.j2ee.vol_uni_edu.models.GroupRecurringDonation;
import com.j2ee.vol_uni_edu.models.UserRecurringDonation;
import com.j2ee.vol_uni_edu.services.GroupRecurringDonationService;
import com.j2ee.vol_uni_edu.services.UserRecurringDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// DonationStatisticsController.java
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/donations")
public class DonationStatisticsController {

    private final UserRecurringDonationService userDonationService;
    private final GroupRecurringDonationService groupDonationService;

    @Autowired
    public DonationStatisticsController(UserRecurringDonationService userDonationService, GroupRecurringDonationService groupDonationService) {
        this.userDonationService = userDonationService;
        this.groupDonationService = groupDonationService;
    }

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, List<?>>> getDonationsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<UserRecurringDonation> userDonations = userDonationService.getDonationsByDateRange(startDate, endDate);
        List<GroupRecurringDonation> groupDonations = groupDonationService.getDonationsByDateRange(startDate, endDate);

        Map<String, List<?>> donations = new HashMap<>();
        donations.put("userDonations", userDonations);
        donations.put("groupDonations", groupDonations);

        return new ResponseEntity<>(donations, HttpStatus.OK);
    }
    @GetMapping("/daily-totals")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getDailyTotalDonations(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<Object[]> userDonations = userDonationService.getUserDailyTotalDonations(startDate, endDate);
        List<Object[]> groupDonations = groupDonationService.getGroupDailyTotalDonations(startDate, endDate);

        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        result.put("userDailyTotals", convertToResponse(userDonations));
        result.put("groupDailyTotals", convertToResponse(groupDonations));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/monthly-totals")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getMonthlyTotalDonations(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<Object[]> userDonations = userDonationService.getUserMonthlyTotalDonations(startDate, endDate);
        List<Object[]> groupDonations = groupDonationService.getGroupMonthlyTotalDonations(startDate, endDate);

        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        result.put("userMonthlyTotals", convertToResponse(userDonations));
        result.put("groupMonthlyTotals", convertToResponse(groupDonations));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/yearly-totals")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getYearlyTotalDonations(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<Object[]> userDonations = userDonationService.getUserYearlyTotalDonations(startDate, endDate);
        List<Object[]> groupDonations = groupDonationService.getGroupYearlyTotalDonations(startDate, endDate);

        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        result.put("userYearlyTotals", convertToResponse(userDonations));
        result.put("groupYearlyTotals", convertToResponse(groupDonations));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<Map<String, Object>> getDonationsByCampaign(@PathVariable Long campaignId) {
        // Lấy danh sách donations của user và group
        List<UserRecurringDonation> userDonations = userDonationService.getDonationsByCampaignId(campaignId);
        List<GroupRecurringDonation> groupDonations = groupDonationService.getGroupRecurringDonationsByCampaignId(campaignId);

        // Chuyển dữ liệu thành một Map để trả về
        Map<String, Object> result = new HashMap<>();
        result.put("userDonations", userDonations);
        result.put("groupDonations", groupDonations);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    private List<Map<String, Object>> convertToResponse(List<Object[]> donations) {
        return donations.stream().map(record -> {
            Map<String, Object> map = new HashMap<>();
            map.put("date", record[0]);
            map.put("totalAmount", record[1]);
            return map;
        }).toList();
    }
}
