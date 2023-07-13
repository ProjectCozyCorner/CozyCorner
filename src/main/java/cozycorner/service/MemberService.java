package cozycorner.service;

import cozycorner.domain.User;
import cozycorner.repository.MemberRepository;
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
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException((userEmail)));
    }

    public Long save(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setUserPwd(encoder.encode(user.getUserPwd()));

        return memberRepository.save(User.builder()
                .userName(user.getUsername())
                .userPhone(user.getUserPhone())
                .email(user.getEmail())
                .userRole(user.getUserRole())
                .userPwd(user.getUserPwd()).build()).getUserId();
    }
}


