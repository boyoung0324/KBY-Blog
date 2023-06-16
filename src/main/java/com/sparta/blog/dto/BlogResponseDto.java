package com.sparta.blog.dto;

import com.sparta.blog.entity.Blog;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlogResponseDto {
    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public BlogResponseDto(Blog blog) {
        this.bno = blog.getBno();
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.writer = blog.getWriter();
        this.createdAt = blog.getCreatedAt();
        this.modifiedAt = blog.getModifiedAt();
    }
}
