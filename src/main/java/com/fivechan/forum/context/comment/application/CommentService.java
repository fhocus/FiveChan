package com.fivechan.forum.context.comment.application;

import com.fivechan.forum.context.comment.domain.Comment;
import com.fivechan.forum.context.comment.domain.CommentRepository;
import com.fivechan.forum.context.comment.domain.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void createComment(UUID userId, UUID topicId, String content) {
        Comment comment = new Comment(userId, topicId, content);
        this.commentRepository.save(comment);
    }

    public void deleteComment(UUID commentId) {
        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(MessageFormat.format("Comment not found with id: {0}", commentId)));
        this.commentRepository.delete(comment);
    }

    public void editComment(UUID commentId, String newContent) {
        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + commentId));
        comment.setContent(newContent);
        this.commentRepository.update(comment);
    }

    public List<Comment> getAllComments() {
        return this.commentRepository.findAll();
    }

    public List<Comment> getCommentsByUserId(UUID userId) {
        return this.commentRepository.findByUserId(userId);
    }

    public Comment getCommentById(UUID commentId) {
        return this.commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + commentId));
    }

    public List<CommentDTO> getCommentsByTopicId(UUID topicId) {
        return this.commentRepository.findByTopicId(topicId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CommentDTO convertToDto(Comment comment) {
        return new CommentDTO(comment.getUserId(), comment.getTopicId(), comment.getContent());
    }

}
