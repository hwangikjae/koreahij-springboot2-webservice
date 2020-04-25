package com.hij.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Posts2UpdateRequestDto {
    private String title2;
    private String content2;

    @Builder
    public Posts2UpdateRequestDto(String title2, String content2){
        this.title2 = title2;
        this.content2 = content2;
    }
}
