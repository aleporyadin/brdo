package com.poriadin.brdo.dto;

import com.poriadin.brdo.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Integer id;
    private String body;
    private Integer postId;
    private User user;
}