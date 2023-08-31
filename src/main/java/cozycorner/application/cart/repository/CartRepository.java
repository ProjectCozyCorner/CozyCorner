package cozycorner.application.cart.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import cozycorner.application.cart.domain.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import static cozycorner.application.cart.domain.QCart.cart;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public int maxCartId(){
        return Integer.parseInt(em.createQuery("select max(c.cartId) from Cart c").getResultList().get(0).toString());
    }

    public void addCart(Cart cart){
        em.persist(cart);
    }

    public List<Cart> findAllCart(String email){
        return em.createQuery("select c from Cart c where c.cartRecogVal = :email order by cartId asc").setParameter("email", email).getResultList();
    }


    public Cart findCartById(Long cartId) {return em.find(Cart.class, cartId);}


    public Long deleteByCartId(Long cartId){
        return queryFactory
                .delete(cart)
                .where(cart.cartId.eq(cartId))
                .execute();
    }
}
