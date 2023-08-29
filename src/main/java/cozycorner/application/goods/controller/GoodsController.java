package cozycorner.application.goods.controller;

import cozycorner.application.goods.domain.Goods;
import cozycorner.application.goods.service.GoodsService;
import cozycorner.application.order.dto.CheckOutForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GoodsController {
    private final GoodsService goodsService;

    @GetMapping("/shop")
    public String shop(Model model) {
        List<Goods> goodsList = goodsService.findGoodsList();
        model.addAttribute("goods", goodsList);
        return "shop";
    }



    @GetMapping("/shopDetail/{goodsId}")
    public String shopDetail(@PathVariable("goodsId") Long goodsId, Model model) {
        goodsService.updateHits(goodsId);
        Goods one = goodsService.findOne(goodsId);
        model.addAttribute("good", one);
        model.addAttribute("checkOutForm", new CheckOutForm());
        return "order/detail";
    }
}
