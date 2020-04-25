package com.hij.book.springboot.web.domain.posts;

import com.hij.book.springboot.domain.posts.Posts;
import com.hij.book.springboot.domain.posts2.Posts2;
import com.hij.book.springboot.domain.posts2.Posts2Repository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Posts2ReposityryTest {
    @Autowired
    Posts2Repository posts2Repository;

    @After
    public void cleanup(){
        posts2Repository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title2 = "테스트게시글";
        String content2 = "테스트본문";

        posts2Repository.save(Posts2.builder()//테이블 posts2에 insert/update쿼리를 실행한다. id값이 있다면 update,없으면 insert
                .title2(title2)
                .content2(content2)
                .author2("FFFF")
                .build());

        //when
        List<Posts2> posts2List = posts2Repository.findAll();   //테이블 posts에 있는 모든 데이터를 조회해 오는 매소드.

        //then
        Posts2 posts2 = posts2List.get(0);
        assertThat(posts2.getTitle2()).isEqualTo(title2);
        assertThat(posts2.getContent2()).isEqualTo(content2);
   }
    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        posts2Repository.save(Posts2.builder()
                .title2("title")
                .content2("content")
                .author2("author")
                .build());

        //when
        List<Posts2> posts2List = posts2Repository.findAll();

        //then
        Posts2 posts2 = posts2List.get(0);

        System.out.println(">>>>>>>>>>> createDate="+posts2.getCreatedDate() + "::: modifiedDate=" + posts2.getModifiedDate());

        assertThat(posts2.getCreatedDate()).isAfter(now);
        assertThat(posts2.getModifiedDate()).isAfter(now);
    }
}
