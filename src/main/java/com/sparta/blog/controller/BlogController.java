package com.sparta.blog.controller;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.entity.Blog;
import com.sparta.blog.service.BlogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {

    private final BlogService service;
    public BlogController(BlogService service){
        this.service = service;
    } //생성자 주입

    @GetMapping("/blog")
    public List<BlogResponseDto> getContent(){
        return service.getContent();
    }


    @GetMapping("/blog/{bno}")
    public BlogResponseDto getContentByBno(@PathVariable Integer bno){
        Blog blog =  service.findContent(bno);
        BlogResponseDto responseDto = new BlogResponseDto(blog);
        return responseDto;
    }

    //작성
    @PostMapping("/blog")
    public BlogResponseDto write(@RequestBody BlogRequestDto requestDto){
        return service.writeContent(requestDto);
    }


    //삭제
    @DeleteMapping("/blog/{bno}")
    public Integer delete(@PathVariable Integer bno) {
        service.deleteContent(bno);
        return bno;
    }


    //수정
    @PutMapping("/blog/{bno}")
    public Integer update(@PathVariable Integer bno,@RequestBody BlogRequestDto requestDto){
        return service.updateContent(bno,requestDto);
    }





}
