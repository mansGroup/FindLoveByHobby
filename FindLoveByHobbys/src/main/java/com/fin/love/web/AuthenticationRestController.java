package com.fin.love.web;

import com.fin.love.dto.member.FindUserPasswordDto;
import com.fin.love.dto.member.FindUseridDto;
import com.fin.love.respository.member.Member;
import com.fin.love.service.MemberService;
import com.fin.love.service.mail.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.session.SimpleRedirectInvalidSessionStrategy;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/authentication")
@RestController
public class AuthenticationRestController {

    private final EmailSenderService emailSenderService;
    private final MemberService memberService;

    @GetMapping("/findpassword/{userId}/{email}")
    public ResponseEntity<FindUserPasswordDto> findPassword(@PathVariable String userId,
                                                                @PathVariable String email) {
        log.info("findPassword(username={}, email={})", userId, email);
        Member member = memberService.getMemberOrElseEmptyEntity(userId);
        FindUserPasswordDto dto = new FindUserPasswordDto();

        if (Objects.equals(member.getId(), null)) {
            dto.setCode("null");
            dto.setUserPassword("null");
            return ResponseEntity.ok(dto);
        }
        String temporaryPassword = EmailSenderService.createKey();
        dto.setUserPassword(temporaryPassword);
        dto.setCode(emailSenderService.sendEmailForFindPassword(email));
        memberService.updatePassword(userId, temporaryPassword);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findid/{username}/{email}")
    public ResponseEntity<FindUseridDto> findId(@PathVariable String username,
                                      @PathVariable String email) {
        log.info("findId(username={}, email={})", username, email);
        List<Member> member = memberService.getMemberInfoByUsername(username);
        int size = member.size();
        FindUseridDto dto = new FindUseridDto();

        for (int i = 0; i < size; i++) {
            if (isEquals(email, member.get(i))) {
                dto.setCode(emailSenderService.sendEmail(email));
                dto.setUserid(member.get(i).getId());
                return ResponseEntity.ok(dto);
            } else if (i == size - 1) {
                if (!isEquals(email, member.get(i))) {
                    dto.setUserid("null");
                    dto.setCode("null");
                    return ResponseEntity.ok(dto);
                }
            }
        }
        return null;
    }

    private static boolean isEquals(String email, Member member) {
        return member.getEmail().equals(email);
    }

}
