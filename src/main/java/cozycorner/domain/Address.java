package cozycorner.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_address")
@Getter @Setter
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_zipcode")
    private String userZipcode;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_address_detail")
    private String userAddressDetail;

    @Column(name = "user_address_nickname")
    private String userAddressNickname;

}
