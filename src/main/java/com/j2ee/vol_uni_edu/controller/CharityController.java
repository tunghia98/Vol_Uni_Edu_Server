package com.j2ee.vol_uni_edu.controller;

import com.j2ee.vol_uni_edu.models.Charity;
import com.j2ee.vol_uni_edu.services.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/charities")
@CrossOrigin(origins = "http://localhost:3000")
public class CharityController {

    @Autowired
    private CharityService charityService;

    // Create or Update Charity
    @PostMapping
    public ResponseEntity<Charity> createCharity(@RequestBody Charity charity) {
        Charity savedCharity = charityService.saveCharity(charity);
        return new ResponseEntity<>(savedCharity, HttpStatus.CREATED);
    }

    // Get Charity by ID
    @GetMapping("/{id}")
    public ResponseEntity<Charity> getCharityById(@PathVariable Long id) {
        Optional<Charity> charity = charityService.getCharityById(id);
        return charity.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get All Charities
    @GetMapping
    public List<Charity> getAllCharities() {
        return charityService.getAllCharities();
    }

    // Deactivate or Delete Charity based on Status
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateOrDeleteCharity(@PathVariable Long id) {
        Optional<Charity> charityOpt = charityService.getCharityById(id);

        if (charityOpt.isPresent()) {
            Charity charity = charityOpt.get();

            // If the status is ACTIVE, deactivate it
            if (charity.getStatus() == Charity.CampaignStatus.ACTIVE) {
                charityService.deactivateCharityById(id);
                return new ResponseEntity<>(HttpStatus.OK); // Status changed to INACTIVE
            } else {
                // If status is not ACTIVE, delete the charity
                charityService.deleteCharityById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Charity deleted
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Charity not found
        }
    }
}
