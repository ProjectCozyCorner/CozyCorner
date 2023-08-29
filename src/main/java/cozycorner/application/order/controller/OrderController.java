package cozycorner.application.order.controller;

import cozycorner.application.address.domain.Address;
import cozycorner.application.goods.domain.Goods;
import cozycorner.application.order.domain.Order;
import cozycorner.application.order.domain.OrderDetail;
import cozycorner.application.order.dto.AddressForm;
import cozycorner.application.order.dto.CheckOutForm;
import cozycorner.application.order.dto.OrderForm;
import cozycorner.application.address.service.AddressService;
import cozycorner.application.goods.service.GoodsService;
import cozycorner.application.order.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Getter
public class OrderController {

    private final OrderService orderService;
    private final GoodsService goodsService;
//    private final TempUserService tempUserService;
    private final AddressService addressService;

    @GetMapping("/order/{userId}/myPage")
    public String myOrderList(@PathVariable("userId") Long userId, Model model){
        List<Order> ordersByUserId = orderService.findOrdersByUserId(userId);
        model.addAttribute("orders", ordersByUserId);
        return "order/orderList";
    }

    @GetMapping("/order/{orderId}/orderDetailList")
    public String myOrderDetailList(@PathVariable("orderId") Long orderId, Model model){
        List<OrderDetail> orderListByOrderId = orderService.findOrderListByOrderId(orderId);
        model.addAttribute("orderDetails", orderListByOrderId);
        return "order/orderDetailList";
    }

    @PostMapping("/order/checkOut")
    public String checkOutForm(@Valid CheckOutForm form, BindingResult result, Model model){
        Goods good = goodsService.findOne(form.getGoodsId());
        List<Address> addresses = addressService.findAllByUserId(form.getUserId());
//        User user = tempUserService.findOne(form.getUserId());
        model.addAttribute("goods", good);
//        model.addAttribute("user", user);
        model.addAttribute("totalPrice", form.getQuantity() * good.getGoodsPrice() + 3500);
        model.addAttribute("form", form);
        model.addAttribute("orderForm", new OrderForm());
        model.addAttribute("addresses", addresses);
        return "order/checkout";
    }

    @PostMapping("/order/new")
    public String order(@Valid OrderForm form){
        System.out.println("form.getUserId() = " + form.getUserId());
        System.out.println("form.getGoodsId() = " + form.getGoodsId());
        System.out.println("form.getOrderCount() = " + form.getOrderCount());
        Address address = new Address();
        address.setUserAddress(form.getReceiverAddress());
        address.setUserAddressDetail(form.getReceiverAddressDetail());
        address.setUserZipcode(form.getReceiverZipcode());
        System.out.println("address = " + address.getUserAddress());
        System.out.println("address = " + address.getUserAddressDetail());
        System.out.println("address = " + address.getUserZipcode());
        orderService.order(form.getUserId(), form.getGoodsId(), form.getOrderCount(), address);
        return "redirect:/home";
    }

    @GetMapping("/order/address/{addressId}")
    @ResponseBody
    public AddressForm AddressForm(@PathVariable("addressId") Long addressId){
        Address address = addressService.findOne(addressId);
        AddressForm addressForm = new AddressForm();
        addressForm.setAddressId(address.getAddressId());
        addressForm.setUserAddress(address.getUserAddress());
        addressForm.setUserAddressDetail(address.getUserAddressDetail());
        addressForm.setUserAddressNickname(address.getUserAddressNickname());
        addressForm.setUserAddressZipcode(address.getUserZipcode());
        return addressForm;
    }
}
