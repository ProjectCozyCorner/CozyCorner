package cozycorner.repository;

import cozycorner.domain.Goods;
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
}
