package com.fivechan.forum.context.comment.infrastructure;

import com.fivechan.forum.context.comment.domain.Comment;
import com.fivechan.forum.context.comment.domain.CommentEntity;
import com.fivechan.forum.context.comment.domain.CommentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class JpaCommentRepository implements CommentRepository {
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    @Transactional
    public void save(Comment comment) {
        CommentEntity commentEntity = CommentEntity.fromDomain(comment);
        entityManager.persist(commentEntity);
    }

    @Override
    @Transactional
    public void update(Comment comment) {
        CommentEntity commentEntity = CommentEntity.fromDomain(comment);
        entityManager.merge(commentEntity);
    }

    @Override
    @Transactional
    public void delete(Comment comment) {
        CommentEntity commentEntity = CommentEntity.fromDomain(comment);
        entityManager.remove(commentEntity);
    }

    @Override
    public Optional<Comment> findById(UUID id) {
        CommentEntity entity = entityManager.find(CommentEntity.class, id);
        return Optional.ofNullable(entity != null ? entity.toDomain() : null);
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<CommentEntity> query = entityManager.createQuery("FROM CommentEntity", CommentEntity.class);
        return query.getResultList().stream()
                .map(CommentEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> findByUserId(UUID userId) {
        TypedQuery<CommentEntity> query = entityManager.createQuery(
                "SELECT c FROM CommentEntity c WHERE c.userId = :userId", CommentEntity.class);
        query.setParameter("userId", userId);
        return query.getResultList().stream()
                .map(CommentEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> findByTopicId(UUID topicId) {
        TypedQuery<CommentEntity> query = entityManager.createQuery(
                "SELECT c FROM CommentEntity c WHERE c.topicId = :topicId", CommentEntity.class);
        query.setParameter("topicId", topicId);
        return query.getResultList().stream()
                .map(CommentEntity::toDomain)
                .collect(Collectors.toList());
    }
}
