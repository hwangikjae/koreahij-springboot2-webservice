package com.hij.book.springboot.web.dto;

import com.hij.book.springboot.domain.posts2.Posts2;
import lombok.Getter;

@Getter
public class Posts2ResponseDto {
    private Long id;
    private String title2;
    private String content2;
    private String author2;
    private String delyn;

    public Posts2ResponseDto(Posts2 entity){
        this.id = entity.getId();
        this.title2 = entity.getTitle2();
        this.content2 = entity.getContent2();
        this.author2 = entity.getAuthor2();
        this.delyn = entity.getDelyn();
    }
}
