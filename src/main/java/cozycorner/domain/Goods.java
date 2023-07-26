package cozycorner.domain;

import cozycorner.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Goods {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goodsId;

    @OneToMany(mappedBy = "goods")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @Column(name = "category_code")
    private Long categoryCode;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "goods_price")
    private Integer goodsPrice;

    @Column(name = "goods_stock")
    private Integer goodsStock;

    @Column(name = "goods_desc")
    private String goodsDesc;

    @Column(name = "goods_reg_date")
    @CreationTimestamp
    private LocalDateTime goodsRegDate;

    @Column(name = "goods_hits")
    private Integer goodsHits;

    public void addStock(int quantity) {this.goodsStock += quantity;}

    public void removeStock(int quantity){
        int restStock = this.goodsStock - quantity;
        if(restStock < 0) {
            throw new NotEnoughStockException("Need More Stock!");
        }
        this.goodsStock = restStock;
    }
}
