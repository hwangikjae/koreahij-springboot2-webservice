package com.hij.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Posts2DeleteRequestDto {
    private String delyn;

    @Builder
    public Posts2DeleteRequestDto(String delyn){
        this.delyn = delyn;
    }
}
