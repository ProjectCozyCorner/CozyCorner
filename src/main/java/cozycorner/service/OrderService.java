package cozycorner.service;

import cozycorner.domain.*;
import cozycorner.repository.GoodsRepository;
import cozycorner.repository.OrderRepository;
import cozycorner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final GoodsRepository goodsRepository;

    public List<Order> findOrdersByUserId(Long userId){
        return orderRepository.findOrderListByUserId(userId);
    }

    public List<OrderDetail> findOrderListByOrderId(Long orderId){
        return orderRepository.findOrderListByOrderId(orderId);
    }

    @Transactional
    public Long order(Long userId, Long goodsId, int count, Address address){
        //엔티티 조회(영속상태의 엔티티 조회를 위함)
        User user = userRepository.findOne(userId);
        Goods good = goodsRepository.findOne(goodsId);

        //주문 상품 생성 (일단 단건) 차후에 장바구니로 주문하는 경우에는 다중으로 확장해야 함
        OrderDetail orderDetail = OrderDetail.createOrderDetail(good, good.getGoodsPrice(), count);

        //주문 생성
        Order order = Order.createOrder(user, address, orderDetail);

        orderRepository.save(order);

        return order.getOrderId();
    }
}
