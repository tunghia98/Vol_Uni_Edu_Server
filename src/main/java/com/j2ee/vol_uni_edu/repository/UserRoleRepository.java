package com.j2ee.vol_uni_edu.repository;

import com.j2ee.vol_uni_edu.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    // Bạn có thể thêm các phương thức tìm kiếm tùy chỉnh nếu cần
}
