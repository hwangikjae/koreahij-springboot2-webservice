package com.hij.book.springboot.web;

import com.hij.book.springboot.domain.posts2.Posts2;
import com.hij.book.springboot.domain.posts2.Posts2Repository;
import com.hij.book.springboot.web.dto.Posts2SaveRequestDto;
import com.hij.book.springboot.web.dto.Posts2UpdateRequestDto;
import com.hij.book.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Posts2ApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private Posts2Repository posts2Repository;

    @After
    public void tearDown() throws Exception {
        posts2Repository.deleteAll();
    }

    @Test
    public void Posts2_등록된다() throws Exception {
        //give
        String title2 = "title2";
        String content2 = "content2";
        Posts2SaveRequestDto requestDto = Posts2SaveRequestDto.builder()
                .title2(title2)
                .content2(content2)
                .author2("FUCK")
                .delyn("N")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts2";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts2> all = posts2Repository.findAll();
        assertThat(all.get(0).getTitle2()).isEqualTo(title2);
        assertThat(all.get(0).getContent2()).isEqualTo(content2);
    }

    @Test
    public void Posts2_수정된다() throws Exception {
        //given
        Posts2 savedPosts2 = posts2Repository.save(Posts2.builder()
        .title2("title")
        .content2("content")
        .author2("FUCK")
        .build());

        Long updateId = savedPosts2.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        Posts2UpdateRequestDto requestDto = Posts2UpdateRequestDto.builder()
                .title2(expectedTitle)
                .content2(expectedContent)
                .build();

        String url = "http://localhost:"+port+"/api/v1/posts2/"+updateId;

        HttpEntity<Posts2UpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts2> all = posts2Repository.findAll();
        assertThat(all.get(0).getTitle2()).isEqualTo(expectedTitle);
        //assertThat(all.get(0).getContent2()).isEqualTo(alterContent2);
        assertThat(all.get(0).getContent2()).isEqualTo(expectedContent);
    }
}
