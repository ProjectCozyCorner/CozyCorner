package cozycorner.controller;

import cozycorner.config.security.CustomUserDetails;
import cozycorner.domain.Cart;
import cozycorner.domain.Goods;
import cozycorner.domain.User;
import cozycorner.dto.CheckOutForm;
import cozycorner.service.CartService;
import cozycorner.service.GoodsService;
import cozycorner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final GoodsService goodsService;
    private final CartService cartService;
    private final UserService userService;

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
    public String cart(Model model, Authentication authentication) {
//        List<Cart> cartList = cartService.findCart(user.getEmail());
//        model.addAttribute("cartList", cartList);
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        System.out.println("customUserDetails = " + customUserDetails.getUserEmail());
        return "cart";
    }

    @PostMapping("/cart/add")
    @ResponseBody
    public String addToCart(@RequestBody HashMap<String, Object> map){
        String goodsId = map.get("goodsId").toString();
        String userId = map.get("userId").toString();
        Goods goods = goodsService.findOne(Long.parseLong(goodsId));
        User user = userService.findOne(Long.parseLong(userId));
        cartService.addToCart(goods, user);
        return "success";
    }

    @GetMapping("/shopDetail/{goodsId}")
    public String shopDetail(@PathVariable("goodsId") Long goodsId, Model model) {
        goodsService.updateHits(goodsId);
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
