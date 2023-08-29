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
        userRepository.save(user);
    }
}
