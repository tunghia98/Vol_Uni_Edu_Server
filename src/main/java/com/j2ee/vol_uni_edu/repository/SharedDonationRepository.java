package com.j2ee.vol_uni_edu.repository;

import com.j2ee.vol_uni_edu.models.SharedDonation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SharedDonationRepository extends JpaRepository<SharedDonation, Long> {
    // Bạn có thể thêm các phương thức tùy chỉnh ở đây nếu cần
}
