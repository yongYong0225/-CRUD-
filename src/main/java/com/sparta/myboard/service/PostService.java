package com.sparta.myboard.service;

import com.sparta.myboard.dto.PostRequestDto;
import com.sparta.myboard.dto.PostResponseDto;
import com.sparta.myboard.entity.Post;
import com.sparta.myboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    //@Transactional -> acid캐시를 깨지지 않게 해줌
    @Transactional (readOnly = true)
    public List<PostResponseDto> getPosts(){
        return postRepository.findAllPostByOrderByCreatedAtDesc().stream().map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto creatPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

        @Transactional(readOnly = true)
        public PostResponseDto getPost(Long id) {
            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );
            return new PostResponseDto(post);
        }

        @Transactional
        public PostResponseDto updatePost(Long id, PostRequestDto requestDto){
            Post post = postRepository.findByIdAndPassword(id, requestDto.getPassword()).orElseThrow(
                    () -> new IllegalArgumentException("비밀번호가 틀립니다.")
            );
            post.update(requestDto);
            postRepository.save(post);

            return new PostResponseDto(post);
        }

    @Transactional
    public boolean deletePost(Long id, String password) {
        if(postRepository.existsByIdAndPassword(id, password)){
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
