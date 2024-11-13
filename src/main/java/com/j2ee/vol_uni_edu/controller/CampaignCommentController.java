package com.j2ee.vol_uni_edu.controller;

import com.j2ee.vol_uni_edu.models.CampaignComment;
import com.j2ee.vol_uni_edu.models.Charity;
import com.j2ee.vol_uni_edu.models.User;
import com.j2ee.vol_uni_edu.services.CampaignCommentService;
import com.j2ee.vol_uni_edu.services.CharityService;
import com.j2ee.vol_uni_edu.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CampaignCommentController {

    @Autowired
    private CampaignCommentService commentService;

    @Autowired
    private CharityService charityService;

    @Autowired
    private UserService userService;

    // Add a new comment to a campaign
    @PostMapping("/{campaignId}/{userId}")
    public ResponseEntity<CampaignComment> addComment(
            @PathVariable Long campaignId, @PathVariable Long userId,
            @RequestBody CampaignComment comment) {

        Optional<Charity> campaignOpt = charityService.getCharityById(campaignId);
        Optional<User> userOpt = userService.getUserById(userId);

        if (campaignOpt.isPresent() && userOpt.isPresent()) {
            comment.setCampaign(campaignOpt.get());
            comment.setUser(userOpt.get());
            CampaignComment savedComment = commentService.addComment(comment);
            return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all comments for a specific campaign
    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<CampaignComment>> getCommentsByCampaign(@PathVariable Long campaignId) {
        Optional<Charity> campaignOpt = charityService.getCharityById(campaignId);
        if (campaignOpt.isPresent()) {
            List<CampaignComment> comments = commentService.getCommentsByCampaign(campaignOpt.get());
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all comments made by a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CampaignComment>> getCommentsByUser(@PathVariable Long userId) {
        Optional<User> userOpt = userService.getUserById(userId);
        if (userOpt.isPresent()) {
            List<CampaignComment> comments = commentService.getCommentsByUser(userOpt.get());
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a comment by ID
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        Optional<CampaignComment> commentOpt = commentService.getCommentById(commentId);
        if (commentOpt.isPresent()) {
            commentService.deleteComment(commentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
