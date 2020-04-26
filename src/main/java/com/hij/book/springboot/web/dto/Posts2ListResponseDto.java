package com.hij.book.springboot.web.dto;

import com.hij.book.springboot.domain.posts2.Posts2;

import java.time.LocalDateTime;

public class Posts2ListResponseDto {
    private Long id;
    private String title2;
    private String author2;
    private LocalDateTime modifiedDate;

    public Posts2ListResponseDto(Posts2 entity){
        this.id = entity.getId();
        this.author2 = entity.getAuthor2();
        this.title2 = entity.getTitle2();
        this.modifiedDate = entity.getModifiedDate();
    }
}
