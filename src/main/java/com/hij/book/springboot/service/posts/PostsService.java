package com.hij.book.springboot.service.posts;

import com.hij.book.springboot.domain.posts.Posts;
import com.hij.book.springboot.domain.posts.PostsRepository;
import com.hij.book.springboot.web.dto.PostsResponseDto;
import com.hij.book.springboot.web.dto.PostsSaveRequestDto;
import com.hij.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
