package com.sparta.blog.entity;

import com.sparta.blog.dto.BlogRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "blog")
@NoArgsConstructor
public class Blog extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer bno;
    @Column(name = "title", nullable = false)
    String title;
    @Column(name = "content", nullable = false, length = 500)
    String content;
    @Column(name = "writer", nullable = false)
    String writer;
    @Column(name = "pwd", nullable = false)
    String pwd;

    public Blog(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.writer = requestDto.getWriter();
        this.pwd = requestDto.getPwd();
    }

    public void update(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }


}
