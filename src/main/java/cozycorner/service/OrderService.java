package cozycorner.service;

import cozycorner.domain.Order;
import cozycorner.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> findOrdersByUserId(Long userId){
        return orderRepository.findOrderListByUserId(userId);
    }
}
