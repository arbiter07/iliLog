package com.ililog.api.service;

import com.ililog.api.domain.Comment;
import com.ililog.api.domain.Post;
import com.ililog.api.exception.CommentNotFound;
import com.ililog.api.exception.InvalidPassword;
import com.ililog.api.exception.PostNotFound;
import com.ililog.api.repository.CommentRepository;
import com.ililog.api.repository.PostRepository;
import com.ililog.api.request.CommentCreate;
import com.ililog.api.request.CommentDelete;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void write(Long postId, CommentCreate request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFound::new);

        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        Comment comment = Comment.builder()
                .author(request.getAuthor())
                .password(encryptedPassword)
                .content(request.getContent())
                .build();

        post.addComment(comment);
    }

    public void delete(Long commentId, CommentDelete request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFound::new);

        String encryptedPassword = comment.getPassword();
        if (!passwordEncoder.matches(request.getPassword(), encryptedPassword)) {
            throw new InvalidPassword();
        }

        commentRepository.delete(comment);
    }
}
