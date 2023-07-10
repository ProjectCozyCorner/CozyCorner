package cozycorner.service;

import cozycorner.domain.User;
import cozycorner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    /*
        회원가입
     */


    /*
        회원조희
     */
//    public List<Member> findUsers() {
//        return memberRepository.findAll();
//    }
//
//    public Member findOne(Long userId) {
//        return memberRepository.findOne(userId);
//    }
//
//    public List<Address> findUserAddresses(Long userId) {
//        return memberRepository.findUserAddressList(userId);
//    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException((userEmail)));
    }

    public Long save(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setUserPwd(encoder.encode(user.getUserPwd()));

        return userRepository.save(User.builder()
                .userName(user.getUsername())
                .userPhone(user.getUserPhone())
                .email(user.getEmail())
                .userNickname(user.getUserNickname())
                .userProfile(user.getUserProfile())
                .emailCheck(user.getEmailCheck())
                .userRole(user.getUserRole())
                .userGrade(user.getUserGrade())
                .userPwd(user.getUserPwd()).build()).getUserId();
    }
}

