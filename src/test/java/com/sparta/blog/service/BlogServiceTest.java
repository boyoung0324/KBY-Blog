package com.sparta.blog.service;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.entity.Blog;
import com.sparta.blog.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogServiceTest {

    @Autowired
    BlogRepository blogRepo;

    @Autowired
    BlogService service;

    @BeforeEach //테스트마다 데이터 넣는 것이 실행되도록
    public void testData() {
        for (int i = 0; i < 100; i++) {
            Blog blog = new Blog();
            blog.setBno(i);
            blog.setTitle("title" + i);
            blog.setContent("content" + i);
            blog.setWriter("writer" + (i % 5));
            blogRepo.save(blog);
        }
    }

    @Test
    void getContent() {
        List<BlogResponseDto> list = blogRepo.findAll().stream().map(BlogResponseDto::new).toList();
        System.out.println("list = " + list);
        assertTrue(list!=null);

    }

    @Test
    void deleteContent() {
        blogRepo.deleteById(10);
    }



    @Test
    void findContent() {
        Blog blog =  blogRepo.findById(5).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모가 존재하지 않습니다."));
        System.out.println("blog = " + blog);

    }
    }





