package com.j2ee.vol_uni_edu.services;

import com.j2ee.vol_uni_edu.models.CampaignComment;
import com.j2ee.vol_uni_edu.models.Charity;
import com.j2ee.vol_uni_edu.models.User;
import com.j2ee.vol_uni_edu.repository.CampaignCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignCommentService {

    @Autowired
    private CampaignCommentRepository commentRepository;

    // Add a new comment
    public CampaignComment addComment(CampaignComment comment) {
        return commentRepository.save(comment);
    }

    // Get all comments for a specific campaign
    public List<CampaignComment> getCommentsByCampaign(Charity campaign) {
        return commentRepository.findByCampaign(campaign);
    }

    // Get all comments made by a specific user
    public List<CampaignComment> getCommentsByUser(User user) {
        return commentRepository.findByUser(user);
    }

    // Get a comment by ID
    public Optional<CampaignComment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    // Delete a comment by ID
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
