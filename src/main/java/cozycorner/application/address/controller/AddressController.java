package cozycorner.application.address.controller;

import cozycorner.application.address.domain.Address;
import cozycorner.application.address.service.AddressService;
import cozycorner.application.order.dto.AddressForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/address/myAddress/{addressId}")
    @ResponseBody
    public AddressForm AddressForm(@PathVariable("addressId") Long addressId){
        Address address = addressService.findOne(addressId);
        AddressForm addressForm = new AddressForm();
        addressForm.setAddressId(address.getAddressId());
        addressForm.setUserAddress(address.getUserAddress());
        addressForm.setUserAddressDetail(address.getUserAddressDetail());
        addressForm.setUserAddressNickname(address.getUserAddressNickname());
        addressForm.setUserAddressZipcode(address.getUserZipcode());
        return addressForm;
    }
}
