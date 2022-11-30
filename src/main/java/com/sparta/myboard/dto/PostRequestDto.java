package com.sparta.myboard.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private String password;
}
