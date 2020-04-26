package com.hij.book.springboot.web;

import com.hij.book.springboot.service.posts.PostsService;
import com.hij.book.springboot.service.posts2.Posts2Service;
import com.hij.book.springboot.web.dto.Posts2ResponseDto;
import com.hij.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final Posts2Service posts2Service;

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts2/save")
    public String posts2Save(){
        return "posts2-save";
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        model.addAttribute("posts2", posts2Service.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

    @GetMapping("/posts2/update/{id}")
    public String posts2Update(@PathVariable Long id, Model model){
        Posts2ResponseDto dto2 = posts2Service.findById(id);
        model.addAttribute("post2", dto2);
        return "posts2-update";
    }

}
