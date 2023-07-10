package cozycorner.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_profile")
    private String userProfile;

    @Column(name = "email_check")
    private String emailCheck;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "user_grade")
    private String userGrade;

    @Column(name = "user_insert_date")
    @Temporal(TemporalType.DATE)
    private Date userInsertDate;

    @Builder
    public User(Long userId, String userPwd, String userName, String userPhone, String email, String userNickname, String userProfile, String emailCheck, String userRole, String userGrade, Date userInsertDate) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userPhone = userPhone;
        this.email = email;
        this.userNickname = userNickname;
        this.userProfile = userProfile;
        this.emailCheck = emailCheck;
        this.userRole = userRole;
        this.userGrade = userGrade;
        this.userInsertDate = userInsertDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : userRole.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return userPwd;
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
