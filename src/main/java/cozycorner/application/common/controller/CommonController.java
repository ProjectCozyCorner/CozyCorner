package cozycorner.application.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
    @GetMapping("/common/home")
    public String home() {
        return "index";
    }

    @GetMapping("/common/contact")
    public String contact() {
        return "common/contact";
    }
}
