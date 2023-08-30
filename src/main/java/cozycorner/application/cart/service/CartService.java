package cozycorner.application.cart.service;

import cozycorner.application.user.domain.User;
import cozycorner.application.cart.domain.Cart;
import cozycorner.application.goods.domain.Goods;
import cozycorner.application.cart.repository.CartRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Setter @Getter
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {
    private final CartRepository cartRepository;

    @Transactional
    public void addToCart(Goods goods, User user){
        Long maxCartId = (long) cartRepository.maxCartId();
        Cart cart = new Cart();
        cart.setCartId(maxCartId+1);
        cart.setGoods(goods);
        cart.setCartRecogVal(user.getUserEmail());
        cart.setProductCount(1);
        cartRepository.addCart(cart);
    }

    @Transactional
    public void deleteFromCart(Long cartId){
        cartRepository.deleteByCartId(cartId);
    }

    public List<Cart> findCart(String email){
        return cartRepository.findAllCart(email);
    }


    public Cart findCartById(Long cartId){return cartRepository.findCartById(cartId);}
}
