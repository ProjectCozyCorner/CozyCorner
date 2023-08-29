package cozycorner.application.order.domain;

import cozycorner.application.user.domain.User;
import cozycorner.application.address.domain.Address;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_order")
@Getter @Setter
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    @Column(name = "order_date")
    @CreationTimestamp
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "receiver_zipcode")
    private String receiverZipcode;

    @Column(name = "receiver_address")
    private String receiverAddress;

    @Column(name = "receiver_address_detail")
    private String receiverAddressDetail;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "receiver_phone")
    private String receiverPhone;

    public void addOrderItem(OrderDetail orderDetail){
        this.orderDetailList.add(orderDetail);
        orderDetail.setOrder(this);
    }

    public static Order createOrder(User user, Address address, OrderDetail... orderDetails){
        Order order = new Order();
        order.setUser(user);
        for(OrderDetail orderDetail : orderDetails){
            order.addOrderItem(orderDetail);
        }
        order.setReceiverAddress(address.getUserAddress());
        order.setReceiverAddressDetail(address.getUserAddressDetail());
        order.setReceiverZipcode(address.getUserZipcode());
        order.setReceiverName(user.getUserName());
        order.setReceiverPhone(user.getUserPhone());
        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    public int getTotalPrice(){
        return orderDetailList.stream()
                .mapToInt(OrderDetail::getGoodsPrice)
                .sum();
    }
}
