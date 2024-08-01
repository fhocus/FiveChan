package com.fivechan.forum.context.comment.domain;

import java.util.UUID;

public class CommentDTO {
    private UUID userId;
    private UUID topicId;
    private String content;

    public CommentDTO(UUID userId, UUID topicId, String content) {
        this.userId = userId;
        this.topicId = topicId;
        this.content = content;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getTopicId() {
        return topicId;
    }

    public void setTopicId(UUID topicId) {
        this.topicId = topicId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
