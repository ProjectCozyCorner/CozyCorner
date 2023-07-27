package cozycorner.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class OrderForm {
    private LocalDateTime orderDateTime;
    private String receiverAddress;
    private String receiverAddressDetail;
    private String receiverName;
    private String receiverPhone;
    private String receiverZipcode;
    private Long userId;
    private Long goodsId;
    private int orderCount;
    private String email;
}
