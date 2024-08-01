package com.fivechan.forum.context.topic.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class TopicEntity {
    @Id
    private UUID id;
    private UUID userId;
    private String title;
    private String content;

    public TopicEntity(UUID id, UUID userId, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public Topic toDomain() {
        return new Topic(id, userId, title, content);
    }

    public static TopicEntity fromDomain(Topic topic) {
        return new TopicEntity(topic.getId(), topic.getUserId(), topic.getTitle(), topic.getContent());
    }
}
