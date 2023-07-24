package cozycorner.controller;

import cozycorner.domain.Order;
import cozycorner.domain.OrderDetail;
import cozycorner.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Getter
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order/{userId}/myPage")
    public String myOrderList(@PathVariable("userId") Long userId, Model model){
        List<Order> ordersByUserId = orderService.findOrdersByUserId(userId);
        model.addAttribute("orders", ordersByUserId);
        return "/Order/orderList";
    }

    @GetMapping("/order/{orderId}/orderDetailList")
    public String myOrderDetailList(@PathVariable("orderId") Long orderId, Model model){
        List<OrderDetail> orderListByOrderId = orderService.findOrderListByOrderId(orderId);
        model.addAttribute("orderDetails", orderListByOrderId);
        return "/Order/orderDetailList";
    }
}
