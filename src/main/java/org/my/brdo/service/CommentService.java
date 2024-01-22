package org.my.brdo.service;

import jakarta.annotation.PostConstruct;
import org.my.brdo.dto.CommentDto;
import org.my.brdo.dto.CommentsResponse;
import org.my.brdo.entity.Comment;
import org.my.brdo.repository.CommentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @PostConstruct
    public void init() {
        fetchAndSaveComments();
    }

    public void fetchAndSaveComments() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            int totalCommentsToFetch = 100;
            int pageSize = 30;
            int currentPage = 0;
            int totalCommentsFetched = 0;

            while (totalCommentsFetched < totalCommentsToFetch) {
                String apiUrl = "https://dummyjson.com/comments?skip=" + (currentPage * pageSize) + "&limit=" + pageSize;
                CommentsResponse response = restTemplate.getForObject(apiUrl, CommentsResponse.class);

                if (response != null && response.getComments() != null) {
                    for (CommentDto commentDto : response.getComments()) {
                        if (totalCommentsFetched >= totalCommentsToFetch) {
                            break;
                        }
                        Comment entityComment = new Comment();
                        entityComment.setBody(commentDto.getBody());
                        entityComment.setPostId(commentDto.getPostId());
                        entityComment.setUsername(Character.toUpperCase(commentDto.getUser().getUsername().charAt(0)) + commentDto.getUser().getUsername().substring(1));
                        entityComment.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("d-M-yyyy H:m:s")));
                        try {
                            commentRepository.save(entityComment);
                        } catch (DataIntegrityViolationException e) {
                            System.out.println("Duplicate comment, not saving: " + entityComment.getBody());
                        }                        totalCommentsFetched++;
                    }
                }
                currentPage++;
            }
        } catch (Exception e) {
            System.out.println("ERROR" + e.getMessage());
        }
    }

    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

}
