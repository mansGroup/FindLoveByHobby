package com.fin.love.member.repository;

import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;
import com.fin.love.respository.member.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@Slf4j
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void makeMember() {

        Timestamp now = new Timestamp(System.currentTimeMillis());

        Member entity = Member.builder()
                .name("이해원")
                .nickname("단비")
                .phone("010-0000-0000")
                .sex(2)
                .id("user1")
                .role(Role.USER)
                .email("danbi@gmail.com")
                .address("경기도 수원시 영통구")
                .birthday(now)
                .password("1")
                .build();

        memberRepository.save(entity);
    }

    @Test
    public void makeMember2() {

        Timestamp now = new Timestamp(System.currentTimeMillis());

        Member entity1 = Member.builder()
                .name("유다한")
                .nickname("다한")
                .phone("010-0000-0000")
                .sex(1)
                .id("user2")
                .role(Role.USER)
                .email("danbi22@gmail.com")
                .address("경기도 수원시 팔달구")
                .birthday(now)
                .password("1")
                .build();

        memberRepository.save(entity1);
    }
    @Test
    public void makeMember3() {

        Timestamp now = new Timestamp(System.currentTimeMillis());

        Member entity2 = Member.builder()
                .name("이찬희")
                .nickname("찬희")
                .phone("010-0000-0000")
                .sex(1)
                .id("user3")
                .role(Role.USER)
                .email("danbi22@gmail.com")
                .address("서울특별시 어딘가")
                .birthday(now)
                .password("1")
                .build();

        memberRepository.save(entity2);
    }
}
