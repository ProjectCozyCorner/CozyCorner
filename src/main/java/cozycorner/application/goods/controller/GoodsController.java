package cozycorner.application.goods.controller;

import cozycorner.application.goods.domain.Goods;
import cozycorner.application.goods.service.GoodsService;
import cozycorner.application.order.dto.CheckOutForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GoodsController {
    private final GoodsService goodsService;

    @GetMapping("/goods/shop")
    public String shop(Model model) {
        List<Goods> goodsList = goodsService.findGoodsList();
        model.addAttribute("goods", goodsList);
        return "goods/shop";
    }



    @GetMapping("/goods/shopDetail/{goodsId}")
    public String shopDetail(@PathVariable("goodsId") Long goodsId,
                             HttpServletRequest request,
                             HttpServletResponse response,
                             Model model) {

        Goods good = goodsService.findOne(goodsId);
        //쿠키 기반 조건에 조건에 만족하는 경우에만 조회수 증가(조회수 어뷰징)
        viewCountValidation(good, request, response);

        model.addAttribute("good", good);
        model.addAttribute("checkOutForm", new CheckOutForm());
        return "goods/detail";
    }

    private void viewCountValidation(Goods good, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;

        boolean isCookie = false;

        //request에 쿠키가 있는 경우
        for(int c=0; cookies != null && c < cookies.length; c++){
            //goodsView 쿠키가 있는 경우
            if(cookies[c].getName().equals("goodsView")){
                cookie = cookies[c];

                //쿠키에 현재 조회한 상품 번호가 없는 경우
                if(!cookie.getValue().contains("[" + good.getGoodsId() +"]")){
                    goodsService.updateHits(good.getGoodsId());
                    cookie.setValue(cookie.getValue() + "[" + good.getGoodsId() +"]");
                    System.out.println("cookie.getValue() = " + cookie.getValue());
                }
                isCookie = true;
                break;
            }
        }

        //쿠키가 없는 경우 처음 조회한 것이기 때문에 쿠키를 새로 생성
        if(!isCookie){
            goodsService.updateHits(good.getGoodsId());
            cookie = new Cookie("goodsView", "[" + good.getGoodsId() +"]");
        }

        //쿠키의 만료시간을 오늘 자정까지로 설정(하루에 한 번만 조회수 증가 가능)
        long todayEndSecond = LocalDate.now().atTime(LocalTime.MAX).toEpochSecond(ZoneOffset.UTC);
        long currentSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        //쿠키는 모든 경로에서 접근가능하도록 경로 설정
        cookie.setPath("/");
        cookie.setMaxAge((int)(todayEndSecond - currentSecond));
        response.addCookie(cookie);
    }
}
