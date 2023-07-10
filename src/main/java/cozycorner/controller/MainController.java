package cozycorner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    @GetMapping("/home")
    public String home(){
        return "index";
    }

    @GetMapping("/shop")
    public String shop(){
        return "shop";
    }

    @GetMapping("/cart")
    public String cart(){
        return "cart";
    }

    @GetMapping("/checkOut")
    public String checkOut(){
        return "checkout";
    }

    @GetMapping("/shopDetail")
    public String shopDetail(){
        return "detail";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }


}
