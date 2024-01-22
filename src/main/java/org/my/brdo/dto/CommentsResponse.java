package org.my.brdo.dto;

import lombok.Data;

@Data
public class CommentsResponse {
    private CommentDto[] comments;
    private int total;
    private int skip;
    private int limit;
}
