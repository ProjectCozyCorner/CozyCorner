package cozycorner.application.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CheckOutForm {
    private Long goodsId;
    private int quantity;
    private String size;
    private String color;
}
