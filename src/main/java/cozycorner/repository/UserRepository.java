package cozycorner.repository;

import cozycorner.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    private final EntityManager em;
//
//    public void save(User user){
//        em.persist(user);
//    }
//
//    public User findOne(Long userId){
//        return em.find(User.class, userId);
//    }
//
//    public List<User> findAll(){
//        return em.createQuery("select u from User u").getResultList();
//    }
//
//    public List<Address> findUserAddressList(Long userId){
//        TypedQuery<Address> query = em.createQuery("select a from Address a where a.user.userId = :userId", Address.class);
//        query.setParameter("userId", userId);
//        return query.getResultList();
//    }
    Optional<User> findByEmail(String email);
}
