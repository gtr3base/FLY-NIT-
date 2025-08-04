package com.gtr3base.BlogApplication.controller;

import com.gtr3base.BlogApplication.dto.CommentDTO;
import com.gtr3base.BlogApplication.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable Long postId) {
        return new ResponseEntity<>(commentService.getCommentsByPostId(postId), HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(
            @PathVariable Long postId, @PathVariable Long commentId) {
        return new ResponseEntity<>(
                commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(
            @PathVariable Long postId, @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(
                commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(
                commentService.updateComment(postId, commentId, commentDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
