package cozycorner.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private String userEmail;
    private String userGrade;
    private String userNickName;
    private String userRole;
    private String userPwd;
    private String userName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(userRole));
        return auth;
    }

    @Override
    public String getPassword() {
        return userPwd;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public String getUserRole() {
        return userRole;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
