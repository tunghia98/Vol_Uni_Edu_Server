package com.j2ee.vol_uni_edu.repository;

import com.j2ee.vol_uni_edu.models.Charity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharityRepository extends JpaRepository<Charity, Long> {

    // Find charity campaigns by title (case-insensitive search)
    List<Charity> findByTitleContainingIgnoreCase(String title);

    // Find all active charity campaigns
    List<Charity> findByStatus(String status);

    // Find charity campaigns created by a specific user
    List<Charity> findByCreatedById(Long userId);

    // Find charity by id and ensure it exists
    Optional<Charity> findById(Long id);

    // Delete charity by id
    void deleteById(Long id);
}
