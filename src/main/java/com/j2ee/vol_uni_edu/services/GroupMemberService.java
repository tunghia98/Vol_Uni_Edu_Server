package com.j2ee.vol_uni_edu.services;

import com.j2ee.vol_uni_edu.models.GroupMember;
import com.j2ee.vol_uni_edu.repository.GroupMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupMemberService {

    private final GroupMemberRepository memberRepository;

    @Autowired
    public GroupMemberService(GroupMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Get all Group Members
    public List<GroupMember> getAllGroupMembers() {
        return memberRepository.findAll();
    }

    // Tạo hoặc cập nhật thành viên nhóm
    public GroupMember saveGroupMember(GroupMember member) {
        return memberRepository.save(member);
    }

    // Lấy thành viên nhóm theo ID
    public Optional<GroupMember> getGroupMemberById(Long id) {
        return memberRepository.findById(id);
    }

    // Lấy tất cả thành viên theo nhóm
    public List<GroupMember> getGroupMembersByGroupId(Long groupId) {
        return memberRepository.findByGroupId(groupId);
    }

    // Lấy tất cả thành viên theo người dùng
    public List<GroupMember> getGroupMembersByUserId(Long userId) {
        return memberRepository.findByUserId(userId);
    }

    // Xoá thành viên nhóm theo ID
    public void deleteGroupMemberById(Long id) {
        memberRepository.deleteById(id);
    }
}
