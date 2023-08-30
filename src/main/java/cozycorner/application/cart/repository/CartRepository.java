package cozycorner.application.cart.repository;

import com.mysema.commons.lang.Assert;
import cozycorner.application.cart.domain.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
        return em.createQuery("select c from Cart c where c.cartRecogVal = :email order by cartId asc").setParameter("email", email).getResultList();
    }


    public Cart findCartById(Long cartId) {return em.find(Cart.class, cartId);}


    @Modifying
    @Query("delete from Cart where cartId = :cartId")
    public void deleteByCartId(@Param("cartId") Long cartId){}
}
