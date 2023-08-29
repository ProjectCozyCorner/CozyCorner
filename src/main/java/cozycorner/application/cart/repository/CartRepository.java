package cozycorner.application.cart.repository;

import cozycorner.application.cart.domain.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartRepository {
    private final EntityManager em;

    public int maxCartId(){
        return Integer.parseInt(em.createQuery("select max(c.cartId) from Cart c").getResultList().get(0).toString());
    }

    public void addCart(Cart cart){
        em.persist(cart);
    }

    public List<Cart> findAllCart(String email){
        return em.createQuery("select c from Cart c where c.cartRecogVal = :email").setParameter("email", email).getResultList();
    }
}
