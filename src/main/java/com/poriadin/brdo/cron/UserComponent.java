package com.poriadin.brdo.cron;


import com.poriadin.brdo.dto.CommentDto;
import com.poriadin.brdo.dto.CommentResponseDto;
import com.poriadin.brdo.entities.User;
import com.poriadin.brdo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserComponent {
    @Value("${brdo.api}")
    private String API_URL;

    private final UserRepository userRepository;

    public UserComponent(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(cron = "0 * * * * *") // CRON вираз для виконання раз на хвилину
    public void fetchCommentsAndSave() {
        RestTemplate restTemplate = new RestTemplate();
        CommentResponseDto response = restTemplate.getForObject(API_URL, CommentResponseDto.class);
        if (response != null && response.getComments() != null) {
            List<CommentDto> comments = response.getComments();
            List<User> users = new ArrayList<>();

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date currentDate = new Date();

            for (CommentDto comment : comments) {
                String username = comment.getUser().getUsername();
                String modifiedUsername = username.substring(0, 1).toUpperCase() + username.substring(1);

                User user = new User();
                user.setBody(comment.getBody());
                user.setPostId(comment.getPostId());
                user.setUsername(modifiedUsername);
                user.setUpdatedAt(dateFormat.format(currentDate));

                users.add(user);
            }

            userRepository.saveAll(users);
        }
    }
}
