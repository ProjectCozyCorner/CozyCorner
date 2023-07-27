package cozycorner.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddressForm {
    private Long addressId;
    private String userAddress;
    private String userAddressDetail;
    private String userAddressNickname;
    private String userAddressZipcode;
}
