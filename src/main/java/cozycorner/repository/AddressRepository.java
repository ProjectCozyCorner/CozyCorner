package cozycorner.repository;

import cozycorner.domain.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AddressRepository {
    private final EntityManager em;

    public List<Address> findAllAddressByUserId(Long userId){
        return em.createQuery("select a from Address a where a.user.userId = :userId").setParameter("userId", userId).getResultList();
    }

    public Address findOne(Long addressId){return em.find(Address.class, addressId);}
}
