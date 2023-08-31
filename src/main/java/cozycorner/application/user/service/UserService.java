package cozycorner.application.user.service;

import cozycorner.application.user.domain.User;
import cozycorner.application.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService{
    private final UserRepository userRepository;

    public void save(User user){
        user.setUserGrade("일반");
        userRepository.save(user);
    }

    public User findByUserEmail(String userEmail){return  userRepository.findByUserEmail(userEmail);}

}
