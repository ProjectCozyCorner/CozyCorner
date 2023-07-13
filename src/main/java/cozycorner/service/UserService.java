package cozycorner.service;

import cozycorner.domain.Address;
import cozycorner.domain.User;
import cozycorner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /*
        회원가입
     */


    /*
        회원조희
     */
    public List<User> findUsers(){
        return  userRepository.findAll();
    }

    public User findOne(Long userId){
        return userRepository.findOne(userId);
    }

    public List<Address> findUserAddresses(Long userId){
        return userRepository.findUserAddressList(userId);
    }
}
