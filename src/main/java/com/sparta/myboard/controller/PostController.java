package com.sparta.myboard.controller;


import com.sparta.myboard.dto.PostDeleteRequestDto;
import com.sparta.myboard.dto.PostDeleteResponseDto;
import com.sparta.myboard.dto.PostRequestDto;
import com.sparta.myboard.dto.PostResponseDto;
import com.sparta.myboard.entity.Post;
import com.sparta.myboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/api/posts") // 전체 게시글 목록 조회
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @PostMapping("/api/post") // 게시글 작성
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto){
        return postService.creatPost(requestDto);
    }

    @GetMapping("/api/post/{id}") // 선택한 게시글 조회
    //@PathVariable => {id}에 들어오는 값을 Long id에 담아줌 (받을데이터가 1개일때)
    //@requestBody => json 형식 (받을 데이터가 여러개일때)
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    @PutMapping("/api/post/{id}") //선택한 게시글 수정
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.updatePost(id, requestDto);
    }

    @DeleteMapping("/api/post/{id}") //선택한 게시글 삭제
    public PostDeleteResponseDto deletePost(@PathVariable Long id, @RequestBody PostDeleteRequestDto requeestDto){
        boolean deleteResult = postService.deletePost(id, requeestDto.getPassword());
        return new PostDeleteResponseDto(deleteResult);
    }
}
