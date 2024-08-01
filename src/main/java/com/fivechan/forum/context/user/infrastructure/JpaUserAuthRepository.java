package com.fivechan.forum.context.user.infrastructure;

import com.fivechan.forum.context.user.domain.UserAuth;
import com.fivechan.forum.context.user.domain.UserAuthEntity;
import com.fivechan.forum.context.user.domain.UserAuthRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class JpaUserAuthRepository implements UserAuthRepository {
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    @Transactional
    public void save(UserAuth userAuth) {
        UserAuthEntity userAuthEntity = UserAuthEntity.fromDomain(userAuth);
        entityManager.persist(userAuthEntity);
    }

    @Override
    public UserAuth findByUsername(String username) {
        UserAuthEntity userAuthEntity = entityManager.find(UserAuthEntity.class, username);
        return userAuthEntity != null ? userAuthEntity.toDomain() : null;
    }

    @Override
    public UserAuth findByEmail(String email) {
        try {
            UserAuthEntity userAuthEntity = entityManager.createQuery("SELECT u FROM UserAuthEntity u WHERE u.email = :email", UserAuthEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return userAuthEntity.toDomain();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteByUsername(String username) {
        UserAuthEntity userAuthEntity = entityManager.find(UserAuthEntity.class, username);
        if (userAuthEntity != null) {
            entityManager.remove(userAuthEntity);
        }
    }
}
