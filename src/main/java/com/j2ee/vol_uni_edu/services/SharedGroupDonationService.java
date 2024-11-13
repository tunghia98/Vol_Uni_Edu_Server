package com.j2ee.vol_uni_edu.services;

import com.j2ee.vol_uni_edu.models.SharedGroupDonation;
import com.j2ee.vol_uni_edu.repository.SharedGroupDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SharedGroupDonationService {

    @Autowired
    private SharedGroupDonationRepository sharedGroupDonationRepository;

    // Lưu hoặc cập nhật một khoản quyên góp nhóm đã chia sẻ
    public SharedGroupDonation saveSharedGroupDonation(SharedGroupDonation sharedGroupDonation) {
        return sharedGroupDonationRepository.save(sharedGroupDonation);
    }

    // Lấy thông tin khoản quyên góp nhóm đã chia sẻ theo ID
    public Optional<SharedGroupDonation> getSharedGroupDonationById(Long id) {
        return sharedGroupDonationRepository.findById(id);
    }

    // Lấy tất cả khoản quyên góp nhóm đã chia sẻ
    public List<SharedGroupDonation> getAllSharedGroupDonations() {
        return sharedGroupDonationRepository.findAll();
    }

    // Xóa khoản quyên góp nhóm đã chia sẻ theo ID
    public void deleteSharedGroupDonationById(Long id) {
        if (sharedGroupDonationRepository.existsById(id)) {
            sharedGroupDonationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Shared group donation not found");
        }
    }
}
