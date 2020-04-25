package com.hij.book.springboot.web;

import com.hij.book.springboot.service.posts2.Posts2Service;
import com.hij.book.springboot.web.dto.Posts2ResponseDto;
import com.hij.book.springboot.web.dto.Posts2SaveRequestDto;
import com.hij.book.springboot.web.dto.Posts2UpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class Posts2ApiController {

    private final Posts2Service posts2Service;

    @PostMapping("/api/v1/posts2")
    public Long Save(@RequestBody Posts2SaveRequestDto requestDto){
        return posts2Service.save(requestDto);
    }

    @PutMapping("/api/v1/posts2/{id}")
    public Long update(@PathVariable Long id, @RequestBody Posts2UpdateRequestDto requestDto){
        return posts2Service.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts2/{id}")
    public Posts2ResponseDto findById(@PathVariable Long id){
        return posts2Service.findById(id);
    }
}
