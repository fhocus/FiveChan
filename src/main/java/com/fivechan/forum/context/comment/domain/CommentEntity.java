package com.fivechan.forum.context.comment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class CommentEntity {
    @Id
    private UUID id;
    private UUID userId;
    private UUID topicId;
    private String content;

    public CommentEntity() {
    }

    public CommentEntity(UUID id, UUID userId, UUID topicId, String content) {
        this.id = id;
        this.userId = userId;
        this.topicId = topicId;
        this.content = content;
    }

    public Comment toDomain() {
        return new Comment(id, userId, topicId, content);
    }

    public static CommentEntity fromDomain(Comment comment) {
        return new CommentEntity(comment.getId(), comment.getUserId(), comment.getTopicId(), comment.getContent());
    }
}
