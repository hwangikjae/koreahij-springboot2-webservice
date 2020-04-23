package com.hij.book.springboot.web;

import com.hij.book.springboot.service.posts.PostsService;
import com.hij.book.springboot.web.dto.PostsResponseDto;
import com.hij.book.springboot.web.dto.PostsSaveRequestDto;
import com.hij.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor//선언된 모든 final 필드가 포함된 생성자를 생성해 준다. final이 없는 필드는 생성자에 포함되지 않는다.
@RestController//컨트롤러를 json을 반환하는 컨트롤러로 만들어 준다.
//예전 @ResponseBody로 각 메소드마다 선언했던 것을 한번에 사용할 수 있게 해 준다.
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }
}
