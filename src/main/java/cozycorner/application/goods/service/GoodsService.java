package cozycorner.application.goods.service;

import cozycorner.application.goods.domain.Goods;
import cozycorner.application.goods.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;

    public Goods findOne(Long goodsId) {return goodsRepository.findOne(goodsId);}

    public List<Goods> findGoodsList(){return goodsRepository.findAll();}

    @Transactional
    public void updateHits(Long goodsId){
        goodsRepository.updateHits(goodsId);
    }
}
