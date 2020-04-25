package com.hij.book.springboot.domain.posts2;

import com.hij.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts2 extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300, nullable = false)
    private String title2;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content2;

    private String author2;

    @Builder
    public Posts2(String title2, String content2, String author2){
        this.title2 = title2;
        this.content2 = content2;
        this.author2 = author2;
    }

    public void update(String title2, String author2){
        this.title2 = title2;
        this.author2 = author2;
    }

}
