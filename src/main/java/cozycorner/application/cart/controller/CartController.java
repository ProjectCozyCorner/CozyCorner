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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final GoodsService goodsService;
    private final CartService cartService;
    private final UserService userService;
    @GetMapping("/cart/list")
    public String cart(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails customUserDetails = (CustomUserDetails) principal;

        List<Cart> cartList = cartService.findCart(customUserDetails.getUsername());
        List<Goods> goodsListByCart = goodsService.findGoodsListByCart(cartList);
        List<HashMap<String, String>> renderCartList = new ArrayList<>();

        for(int c=0; c<cartList.size(); c++){
            HashMap<String, String> tempMap = new HashMap<>();
            tempMap.put("cartId", String.valueOf(cartList.get(c).getCartId()));
            tempMap.put("goodsImgUrl", goodsListByCart.get(c).getGoodsImgUrl());
            tempMap.put("goodsName", goodsListByCart.get(c).getGoodsName());
            tempMap.put("goodsPrice", String.valueOf(goodsListByCart.get(c).getGoodsPrice()));
            tempMap.put("quantity", String.valueOf(cartList.get(c).getProductCount()));
            tempMap.put("totalPrice", String.valueOf(goodsListByCart.get(c).getGoodsPrice() * cartList.get(c).getProductCount()));
            renderCartList.add(tempMap);
        }

        model.addAttribute("cartList", renderCartList);
        return "cart/cartList";
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

    @PostMapping("/cart/delete")
    @ResponseBody
    public HashMap<String, String> deleteFromCart(@RequestBody HashMap<String, Object> map){
        Long cartId = Long.valueOf(map.get("cartId").toString());
        //delete 쿼리가 안됨,,,
        cartService.deleteFromCart(cartId);
        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }
}
