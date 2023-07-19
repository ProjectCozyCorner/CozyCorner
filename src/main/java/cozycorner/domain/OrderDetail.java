package cozycorner.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderDetail {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long orderDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @Column(name = "order_count")
    private Integer orderCount;

    @Column(name = "goods_price")
    private Integer goodsPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "refund_check")
    private String refundCheck;
}
