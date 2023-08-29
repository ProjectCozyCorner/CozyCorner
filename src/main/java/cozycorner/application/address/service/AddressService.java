package cozycorner.application.address.service;

import cozycorner.application.address.domain.Address;
import cozycorner.application.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public List<Address> findAllByUserId(Long userId) {
        return addressRepository.findAllAddressByUserId(userId);
    }

    public Address findOne(Long addressID) {return addressRepository.findOne(addressID);}
}
