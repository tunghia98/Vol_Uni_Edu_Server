package com.j2ee.vol_uni_edu.services;

import com.j2ee.vol_uni_edu.models.SharedDonation;
import com.j2ee.vol_uni_edu.repository.SharedDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SharedDonationService {

    @Autowired
    private SharedDonationRepository sharedDonationRepository;

    // Lưu hoặc cập nhật một khoản quyên góp đã chia sẻ
    public SharedDonation saveSharedDonation(SharedDonation sharedDonation) {
        return sharedDonationRepository.save(sharedDonation);
    }

    // Lấy thông tin khoản quyên góp đã chia sẻ theo ID
    public Optional<SharedDonation> getSharedDonationById(Long id) {
        return sharedDonationRepository.findById(id);
    }

    // Lấy tất cả khoản quyên góp đã chia sẻ
    public List<SharedDonation> getAllSharedDonations() {
        return sharedDonationRepository.findAll();
    }

    // Xóa khoản quyên góp đã chia sẻ theo ID
    public void deleteSharedDonationById(Long id) {
        if (sharedDonationRepository.existsById(id)) {
            sharedDonationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Shared donation not found");
        }
    }
}
