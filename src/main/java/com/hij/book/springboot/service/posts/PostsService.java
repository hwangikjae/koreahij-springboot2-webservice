package com.hij.book.springboot.service.posts;

import com.hij.book.springboot.domain.posts.Posts;
import com.hij.book.springboot.domain.posts.PostsRepository;
import com.hij.book.springboot.web.dto.PostsListResponseDto;
import com.hij.book.springboot.web.dto.PostsResponseDto;
import com.hij.book.springboot.web.dto.PostsSaveRequestDto;
import com.hij.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor//선언된 모든 final 필드가 포함된 생성자를 생성해 준다. final이 없는 필드는 생성자에 포함되지 않는다.
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id==" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당개시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)//트랜잭션 범위는 유지하되, 조회기능만 남겨두어 조회속도가 개선되기 때문에 등록, 수정, 삭제기능이 전혀 없는 서비스 메소드에서 사용하는 것을 추천!!
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public  void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당게시글이 없습니다. id="+id));
        postsRepository.delete(posts);
    }
}
