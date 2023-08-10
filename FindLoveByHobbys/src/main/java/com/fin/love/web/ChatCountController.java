package com.fin.love.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chatCount")
@RestController
public class ChatCountController {

    
    @GetMapping("upCount/{roomId}/{myId}/{mySex}")
    public void upChatCount(@PathVariable Long roomId,
                            @PathVariable String myId,
                            @PathVariable int mySex) {

    }
}
