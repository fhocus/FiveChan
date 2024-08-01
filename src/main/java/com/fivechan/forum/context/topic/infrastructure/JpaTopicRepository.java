package com.fivechan.forum.context.topic.infrastructure;

import com.fivechan.forum.context.topic.domain.Topic;
import com.fivechan.forum.context.topic.domain.TopicEntity;
import com.fivechan.forum.context.topic.domain.TopicRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JpaTopicRepository implements TopicRepository {
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    @Transactional
    public void save(Topic topic) {
        TopicEntity topicEntity = TopicEntity.fromDomain(topic);
        entityManager.persist(topicEntity);
    }

    @Override
    @Transactional
    public void update(Topic topic) {
        TopicEntity topicEntity = TopicEntity.fromDomain(topic);
        entityManager.merge(topicEntity);
    }

    @Override
    @Transactional
    public void delete(Topic topic) {
        TopicEntity topicEntity = TopicEntity.fromDomain(topic);
        entityManager.remove(topicEntity);
    }

    @Override
    public Topic findById(UUID id) {
        return entityManager.find(TopicEntity.class, id).toDomain();
    }

    @Override
    public List<Topic> findAll() {
        TypedQuery<TopicEntity> query = entityManager.createQuery("FROM TopicEntity", TopicEntity.class);
        return query.getResultList().stream()
                .map(TopicEntity::toDomain)
                .toList();
    }

    @Override
    public List<Topic> findByUserId(UUID userId) {
        TypedQuery<TopicEntity> query = entityManager.createQuery(
                "SELECT t FROM TopicEntity t WHERE t.userId = :userId", TopicEntity.class);
        query.setParameter("userId", userId);
        return query.getResultList().stream()
                .map(TopicEntity::toDomain)
                .toList();
    }
}
