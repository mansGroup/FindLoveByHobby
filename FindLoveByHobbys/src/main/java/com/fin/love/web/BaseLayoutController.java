package com.fin.love.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseLayoutController {

    @GetMapping("/baselayout")
    public String baselayout() {
        return "/layout/base_layout";
    }
}
