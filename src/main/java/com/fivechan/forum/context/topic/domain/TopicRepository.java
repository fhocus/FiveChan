package com.fivechan.forum.context.topic.domain;

import java.util.List;
import java.util.UUID;

public interface TopicRepository {
    void save(Topic topic);
    List<Topic> findAll();
    Topic findById(UUID id);
    void update(Topic topic);
    void delete(Topic topic);
    List<Topic> findByUserId(UUID userId);
}
