package com.sparta.myboard.entity;


import com.sparta.myboard.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity // entity로 사용
@NoArgsConstructor
public class Post extends Timestamped{

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column (nullable = false)
    private String author;

    @Column (nullable = false)
    private String content;

    @Column (nullable = false)
    private String password;

    public Post(PostRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
    }

    public void update(PostRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
    }


}
