package com.fin.love.respository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {
    @Query("SELECT u.nickname FROM UserInfo u WHERE u.id IN (SELECT c.otherId FROM ChatRoom c WHERE c.myId = :userId)")
    List<String> findNicknameByUserId(@Param("userId") Long userId);
}
