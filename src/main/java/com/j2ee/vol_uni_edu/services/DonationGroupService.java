package com.j2ee.vol_uni_edu.services;

import com.j2ee.vol_uni_edu.models.DonationGroup;
import com.j2ee.vol_uni_edu.repository.DonationGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationGroupService {

    private final DonationGroupRepository groupRepository;

    @Autowired
    public DonationGroupService(DonationGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    // Tạo hoặc cập nhật nhóm quyên góp
    public DonationGroup saveDonationGroup(DonationGroup group) {
        return groupRepository.save(group);
    }
    // Lấy nhóm quyên góp theo ID
    public Optional<DonationGroup> getDonationGroupById(Long id) {
        return groupRepository.findById(id);
    }
    // Lấy tất cả các nhóm
    public List<DonationGroup> getAllDonationGroups() {
        return groupRepository.findAll();
    }
    // Lấy tất cả các nhóm theo chủ sở hữu
    public List<DonationGroup> getDonationGroupsByOwnerId(Long ownerId) {
        return groupRepository.findByOwnerId(ownerId);
    }

    // Lấy tất cả các nhóm theo chiến dịch
    public List<DonationGroup> getDonationGroupsByCampaignId(Long campaignId) {
        return groupRepository.findByCampaignId(campaignId);
    }

    // Xóa nhóm quyên góp theo ID
    public void deleteDonationGroupById(Long id) {
        groupRepository.deleteById(id);
    }
}
