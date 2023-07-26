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

    @Column(name = "refund_check")
    private String refundCheck;


    public static OrderDetail createOrderDetail(Goods good, int orderPrice, int count){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setGoods(good);
        orderDetail.setGoodsPrice(orderPrice);
        orderDetail.setOrderCount(count);

        good.removeStock(count);
        return orderDetail;
    }
}
