package com.fin.love.web;

import com.fin.love.dto.member.UpdateInfoDto;
import com.fin.love.respository.member.Member;
import com.fin.love.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member/info")
@RestController
public class MemberInfoUpdateRestController {

    private final MemberService memberService;

    @PostMapping("/update")
    public ResponseEntity<UpdateInfoDto> updateUserInfo(@RequestBody UpdateInfoDto dto) {
        log.info(dto.getId());
        UpdateInfoDto alteredDto = memberService.updateUserInfo(dto);

        return ResponseEntity.ok(alteredDto);
    }

}
