package com.fivechan.forum.apps.backend.controllers.topic;

import com.fivechan.forum.context.topic.application.TopicService;
import com.fivechan.forum.context.topic.domain.TopicDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/topic")
public class TopicController {
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<String> createTopic(@RequestBody TopicDTO topic) {
        try {
            UUID id = UUID.randomUUID();
            this.topicService.createTopic(id, topic.getUserId(), topic.getTitle(), topic.getContent());
            return new ResponseEntity<>("Topic created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating topic: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateTopic(@RequestBody TopicDTO topic) {
        try {
            this.topicService.updateTopic(topic.getUserId(), topic.getTitle(), topic.getContent());
            return new ResponseEntity<>("Topic updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating topic: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTopic(@PathVariable UUID id) {
        try {
            this.topicService.deleteTopic(id);
            return new ResponseEntity<>("Topic deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting topic: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
