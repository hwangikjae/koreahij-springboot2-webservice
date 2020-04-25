package com.hij.book.springboot.service.posts2;

import com.hij.book.springboot.domain.posts.Posts;
import com.hij.book.springboot.domain.posts2.Posts2;
import com.hij.book.springboot.domain.posts2.Posts2Repository;
import com.hij.book.springboot.web.dto.Posts2ResponseDto;
import com.hij.book.springboot.web.dto.Posts2SaveRequestDto;
import com.hij.book.springboot.web.dto.Posts2UpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class Posts2Service {
    private final Posts2Repository posts2Repository;

    @Transactional
    public Long save(Posts2SaveRequestDto requestDto){
        return posts2Repository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, Posts2UpdateRequestDto requestDto){
        Posts2 posts2 = posts2Repository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당게시글이 없습니다. id=" + id));
        posts2.update(requestDto.getTitle2(), requestDto.getContent2());

        return id;
    }

    public Posts2ResponseDto findById(Long id){
        Posts2 entity = posts2Repository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당게시글이 없ㅅ브니다. id" + id));
        return new Posts2ResponseDto(entity);
    }
}
