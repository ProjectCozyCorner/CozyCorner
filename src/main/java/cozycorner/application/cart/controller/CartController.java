package cozycorner.application.cart.controller;

import cozycorner.application.cart.domain.Cart;
import cozycorner.application.cart.service.CartService;
import cozycorner.application.goods.domain.Goods;
import cozycorner.application.goods.service.GoodsService;
import cozycorner.application.user.domain.CustomUserDetails;
import cozycorner.application.user.domain.User;
import cozycorner.application.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final GoodsService goodsService;
    private final CartService cartService;
    private final UserService userService;
    @GetMapping("/cart")
    public String cart(Model model, Authentication authentication) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails customUserDetails = (CustomUserDetails) principal;

        List<Cart> cartList = cartService.findCart(customUserDetails.getUsername());
        model.addAttribute("cartList", cartList);
        return "cart/cart";
    }

    @PostMapping("/cart/add")
    @ResponseBody
    public String addToCart(@RequestBody HashMap<String, Object> map){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails customUserDetails = (CustomUserDetails) principal;

        String goodsId = map.get("goodsId").toString();
        Goods goods = goodsService.findOne(Long.parseLong(goodsId));
        User user = userService.findByUserEmail(customUserDetails.getUsername());
        cartService.addToCart(goods, user);
        return "success";
    }
}
