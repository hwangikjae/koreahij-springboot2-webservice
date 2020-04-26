package com.hij.book.springboot.service.posts2;

import com.hij.book.springboot.domain.posts.Posts;
import com.hij.book.springboot.domain.posts2.Posts2;
import com.hij.book.springboot.domain.posts2.Posts2Repository;
import com.hij.book.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public Long delete(Long id, Posts2DeleteRequestDto requestDto){
        Posts2 posts2 = posts2Repository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당게시글이 없습니다. id=" + id));
        posts2.delete(requestDto.getDelyn());
        return id;
    }

    public Posts2ResponseDto findById(Long id){
        Posts2 entity = posts2Repository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당게시글이 없습니다. id" + id));
        return new Posts2ResponseDto(entity);
    }

    @Transactional(readOnly = true)//트랜잭션 범위는 유지하되, 조회기능만 남겨두어 조회속도가 개선되기 때문에 등록, 수정, 삭제기능이 전혀 없는 서비스 메소드에서 사용하는 것을 추천!!
    public List<Posts2ListResponseDto> findAllDesc(){
        return posts2Repository.findAllDesc().stream()
                .map(Posts2ListResponseDto::new)    //= .map(posts2 -> new Posts2ListResponseDto(posts2))
                //postsRepository 결과로 넘어온 posts의 stream을 map을 통해 PostsListResponseDto 변환 -> List로 반환하는 메소드
                .collect(Collectors.toList());
    }
}
