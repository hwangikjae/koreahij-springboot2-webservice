package com.hij.book.springboot.web.dto;

import com.hij.book.springboot.domain.posts2.Posts2;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Posts2SaveRequestDto {
    private String title2;
    private String content2;
    private String author2;

    @Builder
    public Posts2SaveRequestDto(String title2, String content2, String author2){
        this.title2 = title2;
        this.content2 = content2;
        this.author2 = author2;
    }

    public Posts2 toEntity(){
        return Posts2.builder()
                .title2(title2)
                .content2(content2)
                .author2(author2)
                .build();
    }
}
