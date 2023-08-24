package com.fin.love.web;

import com.fin.love.respository.member.Member;
import com.fin.love.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member/info/update")
@Controller
public class MemberInfoUpdateController {

    private final MemberService memberService;
    @GetMapping("/view")
    public String updateView(Model model) {
        log.info("updateView()");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userid = authentication.getName();

        Member member = memberService.getMemberInfo(userid);
        model.addAttribute("member", member);

        return "/memberInfoUpdate/view";
    }
}
