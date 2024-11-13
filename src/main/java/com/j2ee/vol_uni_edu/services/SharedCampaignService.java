package com.j2ee.vol_uni_edu.services;

import com.j2ee.vol_uni_edu.models.SharedCampaign;
import com.j2ee.vol_uni_edu.repository.SharedCampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SharedCampaignService {

    @Autowired
    private SharedCampaignRepository sharedCampaignRepository;

    // Lưu hoặc cập nhật một chiến dịch đã chia sẻ
    public SharedCampaign saveSharedCampaign(SharedCampaign sharedCampaign) {
        return sharedCampaignRepository.save(sharedCampaign);
    }

    // Lấy thông tin chiến dịch đã chia sẻ theo ID
    public Optional<SharedCampaign> getSharedCampaignById(Long id) {
        return sharedCampaignRepository.findById(id);
    }

    // Lấy tất cả chiến dịch đã chia sẻ
    public List<SharedCampaign> getAllSharedCampaigns() {
        return sharedCampaignRepository.findAll();
    }

    // Xóa chiến dịch đã chia sẻ theo ID
    public void deleteSharedCampaignById(Long id) {
        if (sharedCampaignRepository.existsById(id)) {
            sharedCampaignRepository.deleteById(id);
        } else {
            throw new RuntimeException("Shared campaign not found");
        }
    }
}
