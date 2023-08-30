package cozycorner.application.goods.repository;

import cozycorner.application.cart.domain.Cart;
import cozycorner.application.goods.domain.Goods;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GoodsRepository {
    private final EntityManager em;

    public Goods findOne(Long goodsId) {
        return em.find(Goods.class, goodsId);
    }

    public List<Goods> findAll(){
        return em.createQuery("select g from Goods g").getResultList();
    }

    public void updateHits(Long goodsId){
        Goods goods = em.find(Goods.class, goodsId);
        goods.setGoodsHits(goods.getGoodsHits() + 1);
    }
}
