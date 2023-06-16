package com.sparta.blog.dto;

import lombok.Getter;

@Getter
public class BlogRequestDto {

    private String title;
    private String content;
    private String writer;
    private String pwd;

    BlogRequestDto(String pwd){
        this.pwd = pwd;
    }


}
