package cozycorner.application.user.domain;

import cozycorner.application.order.domain.Order;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter @Setter
public class User {
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
    private String userEmail;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_profile")
    private String userProfile;

    @Column(name = "email_check")
    private String emailCheck;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "user_grade")
    private String userGrade;

    @Column(name = "user_insert_date")
    @CreationTimestamp
    private LocalDateTime userInsertDate = LocalDateTime.now();

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
}
