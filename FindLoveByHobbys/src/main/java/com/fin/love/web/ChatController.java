package com.fin.love.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequestMapping("/chat")
@Controller
public class ChatController {

    @GetMapping("/chat")
    public void chat() {

        log.info("chat()");
    }
}


