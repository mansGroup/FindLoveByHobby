package com.fin.love.web;

import com.fin.love.respository.member.Member;
import com.fin.love.service.MemberService;
import com.fin.love.service.mail.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.session.SimpleRedirectInvalidSessionStrategy;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/authentication")
@RestController
public class AuthenticationRestController {

    private final EmailSenderService emailSenderService;
    private final MemberService memberService;

    @GetMapping("/findid/{userid}/{email}")
    public ResponseEntity<String> findId(@PathVariable String userid,
                                      @PathVariable String email) {
        log.info("findId(userid={}, email={})", userid, email);
        Member member = memberService.getMemberInfo(userid);
        String code = "";
        if (member.getEmail().equals(email)) {
             code  = emailSenderService.sendEmail(email);
        } else {
            return ResponseEntity.ok("이메일을 확인해 주세요.");
        }

        return ResponseEntity.ok(code);
    }

}
