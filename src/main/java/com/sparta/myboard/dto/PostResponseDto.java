package com.sparta.myboard.dto;


import com.sparta.myboard.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String title;
    private String author;
    private String content;

    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getModifiedAt();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.content = entity.getContent();

    }
}