package com.j2ee.vol_uni_edu.controller;

import com.j2ee.vol_uni_edu.models.GroupMember;
import com.j2ee.vol_uni_edu.services.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/group-members")
@CrossOrigin(origins = "http://localhost:3000")
public class GroupMemberController {

    private final GroupMemberService memberService;

    @Autowired
    public GroupMemberController(GroupMemberService memberService) {
        this.memberService = memberService;
    }

    // Tạo hoặc cập nhật thành viên nhóm
    @PostMapping
    public ResponseEntity<GroupMember> createOrUpdateGroupMember(@RequestBody GroupMember member) {
        GroupMember savedMember = memberService.saveGroupMember(member);
        return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
    }
    // Get All Group Members
    @GetMapping
    public ResponseEntity<List<GroupMember>> getAllGroupMembers() {
        List<GroupMember> members = memberService.getAllGroupMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }
    // Lấy thành viên nhóm theo ID
    @GetMapping("/{id}")
    public ResponseEntity<GroupMember> getGroupMemberById(@PathVariable Long id) {
        Optional<GroupMember> member = memberService.getGroupMemberById(id);
        return member.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Lấy tất cả thành viên theo nhóm
    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<GroupMember>> getGroupMembersByGroupId(@PathVariable Long groupId) {
        List<GroupMember> members = memberService.getGroupMembersByGroupId(groupId);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    // Lấy tất cả thành viên theo người dùng
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GroupMember>> getGroupMembersByUserId(@PathVariable Long userId) {
        List<GroupMember> members = memberService.getGroupMembersByUserId(userId);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    // Xoá thành viên nhóm theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupMemberById(@PathVariable Long id) {
        if (memberService.getGroupMemberById(id).isPresent()) {
            memberService.deleteGroupMemberById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
