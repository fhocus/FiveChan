package com.fivechan.forum.context.user.infrastructure;

import com.fivechan.forum.context.user.domain.User;
import com.fivechan.forum.context.user.domain.UserEntity;
import com.fivechan.forum.context.user.domain.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JpaUserRepository implements UserRepository {
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    @Transactional
    public void save(User user) {
        UserEntity userEntity = UserEntity.fromDomain(user);
        entityManager.persist(userEntity);
    }

    @Override
    public List<User> findAll() {
        List<UserEntity> userEntities = entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class).getResultList();
        return userEntities.stream().map(UserEntity::toDomain).toList();
    }

    @Override
    public User findById(UUID id) {
        UserEntity userEntity = entityManager.find(UserEntity.class, id);
        return userEntity != null ? userEntity.toDomain() : null;
    }

    @Override
    public User findByUsername(String username) {
        try {
            UserEntity userEntity = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.name = :username", UserEntity.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return userEntity.toDomain();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            UserEntity userEntity = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.email = :email", UserEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return userEntity.toDomain();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        UserEntity userEntity = entityManager.find(UserEntity.class, id);
        if (userEntity != null) {
            entityManager.remove(userEntity);
        }
    }

    @Override
    @Transactional
    public void updateById(UUID id, User user) {
        UserEntity userEntity = entityManager.find(UserEntity.class, id);
        entityManager.merge(userEntity);
    }
}
