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
    public List<PostResponseDto> getPosts(){ //#2 메서드 실행
        return //#7 컨트롤러로 데이터를 리턴해준다
                postRepository.findAllPostByOrderByCreatedAtDesc() //#3 레포지토리가 데이터를 찾아옴
                .stream() //#4 stream 클래스로 찾아온 데이터를 변환시켜줌
                .map(PostResponseDto::new) //#5 PostResponseDto에 레포지토리가 찾아온 데이터를 할당시켜줌
                .collect(Collectors.toList()); //#6 PostResponseDto를 리스트에 담아줌
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
