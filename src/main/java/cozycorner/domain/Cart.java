package cozycorner.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(CartId.class)
@Getter @Setter
public class Cart implements Serializable {

    @Id @Column(name = "cart_id")
    private String cartId;

    @Id @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    private String cartRecogVal;
    private int productCount;
}
