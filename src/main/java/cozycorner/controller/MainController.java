package cozycorner.controller;

import cozycorner.domain.Goods;
import cozycorner.dto.CheckOutForm;
import cozycorner.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final GoodsService goodsService;

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        List<Goods> goodsList = goodsService.findGoodsList();
        model.addAttribute("goods", goodsList);
        return "shop";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/shopDetail/{goodsId}")
    public String shopDetail(@PathVariable("goodsId") Long goodsId, Model model) {
        Goods one = goodsService.findOne(goodsId);
        model.addAttribute("good", one);
        model.addAttribute("checkOutForm", new CheckOutForm());
        return "order/detail";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/myPage")
    public String myPage(){
        return "myPage";
    }

}
