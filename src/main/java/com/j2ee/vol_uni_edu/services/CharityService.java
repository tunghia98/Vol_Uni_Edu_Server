package com.j2ee.vol_uni_edu.services;

import com.j2ee.vol_uni_edu.models.Charity;
import com.j2ee.vol_uni_edu.repository.CharityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharityService {

    @Autowired
    private CharityRepository charityRepository;

    // Create or Update Charity Campaign
    public Charity saveCharity(Charity charity) {
        return charityRepository.save(charity);
    }

    // Get Charity by ID
    public Optional<Charity> getCharityById(Long id) {
        return charityRepository.findById(id);
    }

    // Get all Charity Campaigns
    public List<Charity> getAllCharities() {
        return charityRepository.findAll();
    }

    // Get Charity Campaigns by Title (Partial Match)
    public List<Charity> getCharitiesByTitle(String title) {
        return charityRepository.findByTitleContainingIgnoreCase(title);
    }

    // Get all active Charity Campaigns
    public List<Charity> getActiveCharities() {
        return charityRepository.findByStatus("active");
    }

    // Get Charity Campaigns by User (creator)
    public List<Charity> getCharitiesByUserId(Long userId) {
        return charityRepository.findByCreatedById(userId);
    }

    // Delete Charity by ID
    public void deleteCharityById(Long id) {
        if (charityRepository.existsById(id)) {
            charityRepository.deleteById(id);
        } else {
            throw new RuntimeException("Charity campaign not found");
        }
    }

    // Deactivate Charity Campaign by ID
    public void deactivateCharityById(Long id) {
        Optional<Charity> charity = charityRepository.findById(id);
        if (charity.isPresent()) {
            Charity existingCharity = charity.get();
            existingCharity.setStatus(Charity.CampaignStatus.INACTIVE);
            charityRepository.save(existingCharity);
        } else {
            throw new RuntimeException("Charity campaign not found");
        }
    }
}
