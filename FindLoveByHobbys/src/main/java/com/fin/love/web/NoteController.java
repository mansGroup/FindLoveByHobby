package com.fin.love.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("note")
@Controller
public class NoteController {

    @GetMapping("/note")
    public void note(Model model) {
        String id = "user1";
        model.addAttribute("userId", id);
    }
}