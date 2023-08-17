package cozycorner.service;

import cozycorner.domain.Cart;
import cozycorner.domain.Goods;
import cozycorner.domain.User;
import cozycorner.repository.CartRepository;
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
        cart.setCartRecogVal(user.getEmail());
        cart.setProductCount(1);
        cartRepository.addCart(cart);
    }

    public List<Cart> findCart(String email){

        return cartRepository.findAllCart(email);
    }
}
