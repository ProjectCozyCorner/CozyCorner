package cozycorner.repository;

import cozycorner.domain.Order;
import cozycorner.domain.OrderDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public List<Order> findOrderListByUserId(Long userId) {
        return em.createQuery("select o from Order o where o.user.userId = :userId").setParameter("userId", userId).getResultList();
    }

    public List<OrderDetail> findOrderListByOrderId(Long orderId) {
        return em.createQuery("select o from OrderDetail o where o.order.orderId = :orderId").setParameter("orderId", orderId).getResultList();
    }
}
