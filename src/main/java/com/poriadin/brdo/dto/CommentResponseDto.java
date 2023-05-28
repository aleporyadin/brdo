package com.poriadin.brdo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private List<CommentDto> comments;
    private Integer total;
    private Integer skip;
    private Integer limit;
}
