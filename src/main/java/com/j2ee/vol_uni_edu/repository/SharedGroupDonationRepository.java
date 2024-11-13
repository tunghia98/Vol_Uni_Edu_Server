package com.j2ee.vol_uni_edu.repository;

import com.j2ee.vol_uni_edu.models.SharedGroupDonation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SharedGroupDonationRepository extends JpaRepository<SharedGroupDonation, Long> {
    // Bạn có thể thêm các phương thức tùy chỉnh ở đây nếu cần
}
