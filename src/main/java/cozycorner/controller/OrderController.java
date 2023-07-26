package cozycorner.controller;

import cozycorner.domain.Goods;
import cozycorner.domain.Order;
import cozycorner.domain.OrderDetail;
import cozycorner.domain.User;
import cozycorner.service.GoodsService;
import cozycorner.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Getter
public class OrderController {

    private final OrderService orderService;
    private final GoodsService goodsService;

    @GetMapping("/order/{userId}/myPage")
    public String myOrderList(@PathVariable("userId") Long userId, Model model){
        List<Order> ordersByUserId = orderService.findOrdersByUserId(userId);
        model.addAttribute("orders", ordersByUserId);
        return "/order/orderList";
    }

    @GetMapping("/order/{orderId}/orderDetailList")
    public String myOrderDetailList(@PathVariable("orderId") Long orderId, Model model){
        List<OrderDetail> orderListByOrderId = orderService.findOrderListByOrderId(orderId);
        model.addAttribute("orderDetails", orderListByOrderId);
        return "/order/orderDetailList";
    }

    @PostMapping("/order/checkOut")
    public String checkOutForm(@Valid CheckOutForm form, BindingResult result, Model model){
        Goods one = goodsService.findOne(form.getGoodsId());
        model.addAttribute("goods", one);
        model.addAttribute("totalPrice", form.getQuantity() * one.getGoodsPrice() + 3500);
        model.addAttribute("form", form);
        model.addAttribute("orderForm", new OrderForm());
        return "/order/checkout";
    }

    @PostMapping("/order/new")
    public String order(@Valid OrderForm form){
        System.out.println("form.getUserId() = " + form.getUserId());
        System.out.println("form.getGoodsId() = " + form.getGoodsId());
        System.out.println("form.getOrderCount() = " + form.getOrderCount());
        orderService.order(form.getUserId(), form.getGoodsId(), form.getOrderCount());
        return "redirect:/home";
    }
}
