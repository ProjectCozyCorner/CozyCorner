package cozycorner.cozycorner.queryDsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import cozycorner.application.user.domain.QUser;
import cozycorner.application.user.domain.User;
import cozycorner.application.user.domain.UserRole;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.annotation.Persistent;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;


public class queryDslTest {

    @Persistent
    private EntityManager em;

    @Test
    void selectEntity(){
        User user = new User();
        user.setUserId(20L);
        user.setUserEmail("yhn11@test.com");
        user.setUserPwd("1234");
        user.setUserRole(UserRole.ROLE_USER);
        em.persist(user);

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QUser qUser = new QUser("user");

        User result = jpaQueryFactory
                .selectFrom(qUser)
                .fetchOne();

    }
}
