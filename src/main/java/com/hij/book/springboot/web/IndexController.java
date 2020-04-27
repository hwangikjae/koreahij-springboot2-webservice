package com.hij.book.springboot.web;

import com.hij.book.springboot.config.auth.dto.SessionUser;
import com.hij.book.springboot.service.posts.PostsService;
import com.hij.book.springboot.service.posts2.Posts2Service;
import com.hij.book.springboot.web.dto.Posts2ResponseDto;
import com.hij.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final Posts2Service posts2Service;
    private final HttpSession httpSession;

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
        SessionUser user = (SessionUser) httpSession.getAttribute("user");//CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성했다.
        //즉 로그인 성공시, httpSession.getAttribute("user")에서 값을 가져올 수 있다.
        if(user != null){   //세션에 저장된 값이 있을 때만 모델에 userName으로 등록한다.
            //세션에 저장된 값이 없으면 모델에 아무런 값이 없는 상태이므로 로그인 버튼이 보이게 된다!
            model.addAttribute("userName", user.getName());
        }
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
